package ru.dorogin.run_mentor_bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class RunMentorBotApplication {

	public static void main(String[] args) {
		log.info("Старт приложения");
		SpringApplication.run(RunMentorBotApplication.class, args);
	}

}
