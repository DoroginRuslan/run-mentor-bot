package ru.dorogin.runmentorbot.commands;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TextCommandParserTest {

    @Test
    void testParseCommand_ValidCommandWithParams() {
        String input = "/test 1 2 3";
        UserRequest request = TextCommandParser.parseCommand(input);

        assertEquals("test", request.getCommandPrefix());
        assertEquals(List.of("1", "2", "3"), request.getCommandParams());
    }

    @Test
    void testParseCommand_ValidCommandWithoutParams() {
        String input = "/test";
        UserRequest request = TextCommandParser.parseCommand(input);

        assertEquals("test", request.getCommandPrefix());
        assertTrue(request.getCommandParams().isEmpty());
    }

    @Test
    void testParseCommand_ValidCommandWithSingleParam() {
        String input = "/test 42";
        UserRequest request = TextCommandParser.parseCommand(input);

        assertEquals("test", request.getCommandPrefix());
        assertEquals(List.of("42"), request.getCommandParams());
    }

    @Test
    void testParseCommand_InvalidCommand() {
        String input = "invalidCommand";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            TextCommandParser.parseCommand(input);
        });

        assertEquals("Не удалось распарсить команду: " + input, exception.getMessage());
    }

    @Test
    void testParseCommand_EmptyInput() {
        String input = "";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            TextCommandParser.parseCommand(input);
        });

        assertEquals("Не удалось распарсить команду: " + input, exception.getMessage());
    }

    @Test
    void testParseCommand_CommandWithExtraSpaces() {
        String input = "/test     1    2   3 ";

        UserRequest request = TextCommandParser.parseCommand(input);

        assertEquals("test", request.getCommandPrefix());
        assertEquals(List.of("1", "2", "3"), request.getCommandParams());
    }

    @Test
    void testParseCommand_CommandWithBeginEndExtraSpaces() {
        String input = "     /test 1 2 3   ";

        UserRequest request = TextCommandParser.parseCommand(input);

        assertEquals("test", request.getCommandPrefix());
        assertEquals(List.of("1", "2", "3"), request.getCommandParams());
    }

    @Test
    void testParseCommand_CommandWithTextParams() {
        String input = "/test param параметр";

        UserRequest request = TextCommandParser.parseCommand(input);

        assertEquals("test", request.getCommandPrefix());
        assertEquals(List.of("param", "параметр"), request.getCommandParams());

    }
}
