package ru.dorogin.run_mentor_bot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dorogin.run_mentor_bot.dao.RunDao;
import ru.dorogin.run_mentor_bot.dao.UserDao;
import ru.dorogin.run_mentor_bot.dao.dto.Run;
import ru.dorogin.run_mentor_bot.dao.dto.User;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class RunService {

    private final RunDao runDao;
    private final UserDao userDao;

    @Transactional
    public void addRun(Long userId, Double distance, LocalTime duration, LocalDateTime date) {
        User user = userDao.findById(userId).orElseThrow();
        Run run = new Run()
                .setUser(user)
                .setDistance(distance)
                .setDuration(duration)
                .setDate(date);
        runDao.save(run);
    }

    public void addRun(Run run) {
        runDao.save(run);
    }
}
