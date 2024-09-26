package ru.dorogin.run_mentor_bot.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.dorogin.run_mentor_bot.dao.RunDao;
import ru.dorogin.run_mentor_bot.dao.dto.Run;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component("add_run")
@RequiredArgsConstructor
public class AddRunCommand implements Command {

    private final RunDao runDao;

    @Override
    public SendMessage execute(UserRequest userRequest) {
        List<String> params = userRequest.getCommandParams();
        Run run = new Run()
                .setUser(userRequest.getUser())
                .setDistance(Double.parseDouble(params.get(0)))
                .setDuration(LocalTime.parse(params.get(1)));
        // Возможность установки даты отключаем до лучших времён
//                .setDate(params.size() >= 3
//                        ? LocalDateTime.parse(params.get(2))
//                        : userRequest.getToday());
        runDao.save(run);
        String text = "Ммм, ты пробежал %s километров за %s часов, %s минут, %s секунд. Дата записи: %s".formatted(
                run.getDistance(),
                run.getDuration().getHour(),
                run.getDuration().getMinute(),
                run.getDuration().getSecond(),
                run.getDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm"))
        );
        return new SendMessage(userRequest.getUser().getChatId().toString(), text);
    }
}
