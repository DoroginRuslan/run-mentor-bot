package ru.dorogin.run_mentor_bot.services;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dorogin.run_mentor_bot.dao.UserDao;
import ru.dorogin.run_mentor_bot.dao.dto.User;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    @Transactional
    public User findUser(Message message) {
        return userDao.findById(message.getFrom().getId()).orElseGet(() -> userDao.save(getUserFromTelegramUser(message)));
    }

    private User getUserFromTelegramUser(Message msg) {
        val user = msg.getFrom();
        return new User()
                .setId(user.getId())
                .setUsername(user.getUserName())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setChatId(msg.getChatId());
    }
}
