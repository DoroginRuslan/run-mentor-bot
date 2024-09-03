package ru.dorogin.runmentorbot.commands;

import org.telegram.telegrambots.meta.api.objects.Message;

public class AddRunCommand implements Command{
    @Override
    public String execute(Message message) {
        return "Может добавим...";
    }
}
