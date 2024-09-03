package ru.dorogin.runmentorbot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.dorogin.runmentorbot.commands.Command;
import ru.dorogin.runmentorbot.commands.CommandFactory;


@Slf4j
public class RunMentorBot extends TelegramLongPollingBot {
    private final String botUsername;
    private final String botToken;
    private final CommandFactory commandFactory;

    public RunMentorBot(String botUsername, String botToken) {
        this.botUsername = botUsername;
        this.botToken = botToken;
        commandFactory = new CommandFactory();
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info(update.toString());
        Message message = update.getMessage();
        try {
            Command command = commandFactory.getCommand(message);
            String reply = command.execute(message);
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
            e.printStackTrace(); // обработка исключения
        }
    }
}
