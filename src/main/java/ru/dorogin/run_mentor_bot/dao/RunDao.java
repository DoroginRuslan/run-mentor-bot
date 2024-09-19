package ru.dorogin.run_mentor_bot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dorogin.run_mentor_bot.dao.dto.Run;

@Repository
public interface RunDao extends JpaRepository<Run, Long> {
}
