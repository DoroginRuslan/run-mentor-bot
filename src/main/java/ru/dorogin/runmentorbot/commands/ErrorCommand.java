package ru.dorogin.runmentorbot.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component("error")
public class ErrorCommand implements Command{
    @Override
    public String execute(Message message) {
        return "Неизвестная команда";
    }
}
