package ru.dorogin.runmentorbot.commands;

import org.telegram.telegrambots.meta.api.objects.Message;

public class StartCommand implements Command{
    @Override
    public String execute(Message message) {
        return "Добро пожаловать! Используйте команды для отслеживания ваших пробежек.";
    }
}
