package ru.dorogin.run_mentor_bot.commands;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class UserRequest {
    private String commandPrefix;
    private List<String> commandParams;
}
