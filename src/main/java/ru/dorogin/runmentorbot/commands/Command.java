package ru.dorogin.runmentorbot.commands;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface Command {
    String execute(Message message);
}
