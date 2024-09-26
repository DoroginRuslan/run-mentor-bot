package ru.dorogin.run_mentor_bot.utils;

import org.flywaydb.core.internal.util.AsciiTable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AsciiTableFormatterImpl implements TableFormatter {
    @Override
    public String toTelegramTable(List<String> headers, List<List<String>> rows) {
        return new AsciiTable(headers, rows, true, "null", "").render();
    }
}
