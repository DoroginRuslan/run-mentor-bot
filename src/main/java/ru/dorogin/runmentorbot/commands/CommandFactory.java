package ru.dorogin.runmentorbot.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class CommandFactory {

    private final Map<String, Command> commandMap;

    public Command getCommand(String commandPrefix) {
        try {
            return commandMap.getOrDefault(commandPrefix, commandMap.get("error"));
        } catch (Exception e) {
            return commandMap.get("error");
        }
    }
}
