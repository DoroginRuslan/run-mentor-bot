package ru.dorogin.run_mentor_bot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dorogin.run_mentor_bot.dao.UserDao;
import ru.dorogin.run_mentor_bot.dao.dto.User;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    @Transactional
    public User findUser(org.telegram.telegrambots.meta.api.objects.User from) {
        return userDao.findById(from.getId()).orElseGet(() -> userDao.save(getUserFromTelegramUser(from)));
    }

    private User getUserFromTelegramUser(org.telegram.telegrambots.meta.api.objects.User from) {
        return new User()
                .setId(from.getId())
                .setUsername(from.getUserName())
                .setFirstName(from.getFirstName())
                .setLastName(from.getLastName());
    }
}
