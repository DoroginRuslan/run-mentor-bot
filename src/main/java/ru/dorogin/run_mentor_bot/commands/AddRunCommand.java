package ru.dorogin.run_mentor_bot.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.dorogin.run_mentor_bot.dao.RunDao;
import ru.dorogin.run_mentor_bot.dao.dto.Run;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component("add_run")
@RequiredArgsConstructor
public class AddRunCommand implements Command {

    private final RunDao runDao;

    @Override
    public String execute(UserRequest userRequest) {
        List<String> params = userRequest.getCommandParams();
        Run run = new Run()
                .setUser(userRequest.getUser())
                .setDistance(Double.parseDouble(params.get(0)))
                .setDuration(LocalTime.parse(params.get(1)))
                .setDate(params.size() >= 3
                        ? LocalDateTime.parse(params.get(2))
                        : userRequest.getToday());
        runDao.save(run);
        return "Ммм, ты пробежал %s километров за %s. Дата записи: %s".formatted(
                run.getDistance(),
                run.getDuration(),
                run.getDate()
        );
    }
}
