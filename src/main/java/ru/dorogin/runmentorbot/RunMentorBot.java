package ru.dorogin.runmentorbot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Slf4j
public class RunMentorBot extends TelegramLongPollingBot {
    private final String botUsername;
    private final String botToken;

    public RunMentorBot(String botUsername, String botToken) {
        this.botUsername = botUsername;
        this.botToken = botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info(update.toString());
        Message msg = update.getMessage();
        try {
            sendMessage(msg.getChatId().toString(), msg.getText());
        } catch (RuntimeException e) {
            log.error("Error generating or sending QR code: {}", e.getMessage());
            sendMessage(String.valueOf(msg.getChatId()), "Ошибка при генерации QR-кода.");
        }
    }

    public void sendMessage(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message); // метод отправки сообщения
        } catch (TelegramApiException e) {
            e.printStackTrace(); // обработка исключения
        }
    }
}
