package ru.dorogin.run_mentor_bot.dao.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.time.LocalTime;
@Entity
@Table(name = "Runs")
@Setter
@Getter
@Accessors(chain = true)
public class Run {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JoinColumn(name = "distance", nullable = false)
    private Double distance;

    @JoinColumn(name = "duration", nullable = false)
    private LocalTime duration;

    @Temporal(TemporalType.TIMESTAMP)
    @JoinColumn(name = "date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime date;

    @PrePersist
    protected void onCreate() {
        if(date == null) {
            date = LocalDateTime.now();
        }
    }
}
