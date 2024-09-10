package ru.dorogin.runmentorbot.commands;

import org.telegram.telegrambots.meta.api.objects.Message;

public class ErrorCommand implements Command{
    @Override
    public String execute(Message message) {
        return "Неизвестная команда";
    }
}
