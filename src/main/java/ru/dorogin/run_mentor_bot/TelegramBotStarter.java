package ru.dorogin.run_mentor_bot;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@RequiredArgsConstructor
@Slf4j
public class TelegramBotStarter {
    private final RunMentorBot runMentorBot;

    @PostConstruct
    public void startListeningBot() throws TelegramApiException {
        log.info("Старт сессии");
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        BotSession runMentorBotSession = botsApi.registerBot(runMentorBot);
        assert runMentorBotSession.isRunning();
        log.info("Бот запущен");
    }
}
