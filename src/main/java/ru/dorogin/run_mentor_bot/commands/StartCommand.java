package ru.dorogin.run_mentor_bot.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component("start")
public class StartCommand implements Command{
    @Override
    public SendMessage execute(UserRequest userRequest) {
        return new SendMessage(userRequest.getUser().getChatId().toString(),
                "Добро пожаловать! Используйте команды для отслеживания ваших пробежек.");
    }
}
