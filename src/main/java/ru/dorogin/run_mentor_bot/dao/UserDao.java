package ru.dorogin.run_mentor_bot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dorogin.run_mentor_bot.dao.dto.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

}
