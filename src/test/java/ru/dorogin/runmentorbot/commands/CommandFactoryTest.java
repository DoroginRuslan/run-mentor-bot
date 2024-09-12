package ru.dorogin.runmentorbot.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CommandFactoryTest {

    private CommandFactory commandFactory;
    private Command validCommand;
    private Command errorCommand;

    @BeforeEach
    void setUp() {
        Map<String, Command> commandMap = new HashMap<>();
        validCommand = Mockito.mock(Command.class);
        errorCommand = Mockito.mock(Command.class);

        commandMap.put("valid", validCommand);
        commandMap.put("error", errorCommand);

        commandFactory = new CommandFactory(commandMap);
    }

    @Test
    void testGetCommand_ValidCommand() {
        String input = "/valid";  // Пример команды, которую можно распарсить
        Command command = commandFactory.getCommand(input);

        assertEquals(validCommand, command);
    }

    @Test
    void testGetCommand_InvalidCommand() {
        String input = "/invalid";  // Команда, которой нет в commandMap
        Command command = commandFactory.getCommand(input);

        assertEquals(errorCommand, command);
    }

    @Test
    void testGetCommand_ExceptionDuringParsing() {
        String input = "invalidCommand";  // Неправильный формат команды, вызовет исключение

        Command command = commandFactory.getCommand(input);

        assertEquals(errorCommand, command);
    }
}
