package ru.dorogin.runmentorbot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class BotStarter {
    public static void main(String[] args) throws TelegramApiException, IOException {
        log.info("Запуск агрегатора ботов");
        log.info("Загрузка конфиг файла");
        Properties props = loadConfig();
        log.info("Инициализация бота");
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        log.info("Старт сессии");
        BotSession qrBotSession = botsApi.registerBot(new RunMentorBot(
                props.getProperty("botUsername"),
                props.getProperty("botToken")));
        log.info("RunMentorBot статус = " + (qrBotSession.isRunning() ? "Running" : "Error"));
    }

    private static Properties loadConfig() throws IOException {
        Properties properties = new Properties();
        FileInputStream input = new FileInputStream("config.properties");
        properties.load(input);
        return properties;
    }
}
