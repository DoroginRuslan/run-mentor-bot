package ru.dorogin.run_mentor_bot.services;

import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import ru.dorogin.run_mentor_bot.dao.dto.User;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserService userService;

    public void saveUser(User user) {
        userService.saveUser(user);
    }

}
