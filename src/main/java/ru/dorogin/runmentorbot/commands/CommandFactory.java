package ru.dorogin.runmentorbot.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CommandFactory {

    private final Map<String, Command> commandMap;

    public Command getCommand(Message message) {
        return commandMap.getOrDefault(message.getText(), commandMap.get("error"));
    }

    private String getCommandPrefix(Message message) {
        String text = message.getText()
                .trim();
        String[] parts = text.split(" ");
        return Arrays.stream(parts).findFirst().orElse(null);
    }
}
