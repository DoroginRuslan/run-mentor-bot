package ru.dorogin.runmentorbot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.dorogin.runmentorbot.commands.Command;
import ru.dorogin.runmentorbot.commands.CommandFactory;
import ru.dorogin.runmentorbot.commands.TextCommandParser;
import ru.dorogin.runmentorbot.commands.UserRequest;


@Component
@Slf4j
@RequiredArgsConstructor
public class RunMentorBot extends TelegramLongPollingBot {

    private final TelegramBotConfig telegramBotConfig;
    private final CommandFactory commandFactory;

    @Override
    public String getBotUsername() {
        return telegramBotConfig.getUsername();
    }

    @Override
    public String getBotToken() {
        return telegramBotConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info(update.toString());
        Message message = update.getMessage();
        try {
            UserRequest userRequest = TextCommandParser.parseCommand(message.getText());
            Command command = commandFactory.getCommand(userRequest.getCommandPrefix());
            String reply = command.execute(userRequest);
            sendMessage(message.getChatId().toString(), reply);
        } catch (RuntimeException e) {
            log.error("Ошибка обработки сообщения: {}", e.getMessage());
            sendMessage(message.getChatId().toString(), "Ошибка");
        }
    }

    public void sendMessage(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message); // метод отправки сообщения
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки сообщения", e);
        }
    }
}
