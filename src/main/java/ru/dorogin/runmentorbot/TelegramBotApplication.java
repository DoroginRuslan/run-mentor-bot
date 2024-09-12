package ru.dorogin.runmentorbot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
@Slf4j
public class TelegramBotApplication {
    public static void main(String[] args) throws TelegramApiException {
        log.info("Старт приложения");
        SpringApplication.run(TelegramBotApplication.class, args);
    }
}
