package ru.dorogin.run_mentor_bot.commands;

import org.springframework.stereotype.Component;

@Component("error")
public class ErrorCommand implements Command{
    @Override
    public String execute(UserRequest userRequest) {
        return "Неизвестная команда";
    }
}
