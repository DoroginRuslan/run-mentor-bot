package ru.dorogin.runmentorbot.commands;

import org.springframework.stereotype.Component;

@Component("error")
public class ErrorCommand implements Command{
    @Override
    public String execute(UserRequest userRequest) {
        return "Неизвестная команда";
    }
}
