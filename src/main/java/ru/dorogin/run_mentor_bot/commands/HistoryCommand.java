package ru.dorogin.run_mentor_bot.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.dorogin.run_mentor_bot.dao.RunDao;
import ru.dorogin.run_mentor_bot.dao.dto.Run;
import ru.dorogin.run_mentor_bot.utils.TableFormatter;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Component("history")
@RequiredArgsConstructor
public class HistoryCommand implements Command {

    private final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
    private final static DateTimeFormatter tf = DateTimeFormatter.ofPattern("hh:mm:ss");

    private final RunDao runDao;
    private final TableFormatter tableFormatter;

    @Override
    public SendMessage execute(UserRequest userRequest) {
        List<Run> runs = runDao.findAll();
        List<String> headers =  List.of("Дата", "Пользователь", "Дист.", "Время");
        List<List<String>> rows = convertRunsToRows(runs);
        SendMessage sm = new SendMessage(userRequest.getUser().getChatId().toString(),
                "История пробежек:\n" +
                        "```" +
                        tableFormatter.toTelegramTable(headers, rows) +
                        "```");
        sm.enableMarkdownV2(true);
        return sm;
    }

    private List<List<String>> convertRunsToRows(List<Run> runs) {
        return runs.stream()
                .sorted(Comparator.comparing(Run::getDate))
                .map((a) -> List.of(
                        a.getDate().format(dtf),
                        a.getUser().getUsername(),
                        a.getDistance().toString(),
                        a.getDuration().format(tf)))
                .toList();
    }
}
