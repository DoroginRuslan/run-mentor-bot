package ru.dorogin.run_mentor_bot.dao.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class User {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
