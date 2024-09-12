package ru.dorogin.runmentorbot.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component("add_run")
public class AddRunCommand implements Command{
    @Override
    public String execute(Message message) {
        return "Может добавим...";
    }
}
