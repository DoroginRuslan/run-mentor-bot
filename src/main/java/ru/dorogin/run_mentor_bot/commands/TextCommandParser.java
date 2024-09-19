package ru.dorogin.run_mentor_bot.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextCommandParser {
    private static final String COMMAND_PATTERN = "^/(?<command>\\w+)(?:\\s+(?<params>[^\\s]+(?:\\s+[^\\s]+)*))?$";

    public static UserRequest parseCommand(String commandInput) {
        Pattern pattern = Pattern.compile(COMMAND_PATTERN);
        Matcher matcher = pattern.matcher(commandInput.trim());

        if (matcher.matches()) {
            String command = matcher.group("command");
            String paramsString = matcher.group("params");
            List<String> params;

            if (paramsString != null) {
                String[] paramsArray = paramsString.trim().split("\\s+");
                params = Arrays.asList(paramsArray);
            } else {
                params = Collections.emptyList();
            }
            return new UserRequest()
                    .setCommandPrefix(command)
                    .setCommandParams(params);
        } else {
            throw new RuntimeException("Не удалось распарсить команду: " + commandInput);
        }
    }
}
