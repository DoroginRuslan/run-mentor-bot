package ru.dorogin.run_mentor_bot.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component("error")
public class ErrorCommand implements Command{
    @Override
    public SendMessage execute(UserRequest userRequest) {
        return new SendMessage(userRequest.getUser().getChatId().toString(), "Неизвестная команда");
    }
}
