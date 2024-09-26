package ru.dorogin.run_mentor_bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.dorogin.run_mentor_bot.commands.Command;
import ru.dorogin.run_mentor_bot.commands.CommandFactory;
import ru.dorogin.run_mentor_bot.commands.TextCommandParser;
import ru.dorogin.run_mentor_bot.commands.UserRequest;
import ru.dorogin.run_mentor_bot.dao.dto.User;
import ru.dorogin.run_mentor_bot.services.UserService;


@Component
@Slf4j
public class RunMentorBot extends TelegramLongPollingBot {

    private final TelegramBotConfig telegramBotConfig;
    private final CommandFactory commandFactory;
    private final UserService userService;

    public RunMentorBot(TelegramBotConfig telegramBotConfig, CommandFactory commandFactory, UserService userService) {
        super(telegramBotConfig.getToken());
        this.telegramBotConfig = telegramBotConfig;
        this.commandFactory = commandFactory;
        this.userService = userService;
    }

    @Override
    public String getBotUsername() {
        return telegramBotConfig.getUsername();
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info(update.toString());
        Message message = update.getMessage();
        SendMessage sm;
        try {
            User user = userService.findUser(message);
            UserRequest userRequest = TextCommandParser.parseCommand(message.getText());
            userRequest.setUser(user);
            Command command = commandFactory.getCommand(userRequest.getCommandPrefix());
            sm = command.execute(userRequest);
        } catch (RuntimeException e) {
            log.error("Ошибка обработки сообщения: ", e);
            sm = new SendMessage(message.getChatId().toString(), "Ошибка");
        }
        sendMessage(sm);
    }

    private void sendMessage(SendMessage reply) {
        try {
            execute(reply); // метод отправки сообщения
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки сообщения", e);
        }
    }
}
