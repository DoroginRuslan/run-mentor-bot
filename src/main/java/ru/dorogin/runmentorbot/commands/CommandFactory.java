package ru.dorogin.runmentorbot.commands;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;

public class CommandFactory {
    public Command getCommand(Message message) {
        return switch (getCommandPrefix(message)) {
            case "/start" -> new StartCommand();
            case "/add_run" -> new AddRunCommand();
            default -> null;
        };
    }

    private String getCommandPrefix(Message message) {
        String text = message.getText()
                .trim();
        String[] parts = text.split(" ");
        return Arrays.stream(parts).findFirst().orElse(null);
    }
}
