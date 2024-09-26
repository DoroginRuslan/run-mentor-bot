package ru.dorogin.run_mentor_bot.utils;

import java.util.List;

public interface TableFormatter {
    String toTelegramTable(List<String> headers, List<List<String>> rows);
}
