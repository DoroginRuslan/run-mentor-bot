package ru.dorogin.run_mentor_bot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Command {
    SendMessage execute(UserRequest userRequest);
}
