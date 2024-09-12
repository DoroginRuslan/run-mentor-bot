package ru.dorogin.runmentorbot.commands;

import org.springframework.stereotype.Component;

import java.util.List;

@Component("add_run")
public class AddRunCommand implements Command{
    @Override
    public String execute(UserRequest userRequest) {
        List<String> params = userRequest.getCommandParams();
        return "Ммм, ты пробежал %s километров за %s минут...".formatted(
                params.get(0),
                params.get(1)
        );
    }
}
