package ru.dorogin.runmentorbot.commands;

import org.springframework.stereotype.Component;

@Component("start")
public class StartCommand implements Command{
    @Override
    public String execute(UserRequest userRequest) {
        return "Добро пожаловать! Используйте команды для отслеживания ваших пробежек.";
    }
}
