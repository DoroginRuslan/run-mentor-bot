package ru.dorogin.runmentorbot;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class BotStarter {
    public static void main(String[] args) throws TelegramApiException, IOException, ClassNotFoundException, SQLException {
        log.info("Запуск агрегатора ботов");
        log.info("Загрузка конфиг файла");
        Properties props = loadConfig();
        log.info("Инициализация подключения к БД");
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(props.getProperty("db.url"),
                props.getProperty("db.login"),
                props.getProperty("db.pass"));
        log.info("Проверка миграций БД");
        Flyway flyway = Flyway.configure()
                .dataSource(props.getProperty("db.url"),
                        props.getProperty("db.login"),
                        props.getProperty("db.pass"))
                .load();
        flyway.migrate();
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
