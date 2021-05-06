package by.tourist.bot;

import by.tourist.entity.City;
import by.tourist.services.implementation.CityServiceImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private CityServiceImpl cityService;

    public TelegramBot(CityServiceImpl cityService) {
        this.cityService = cityService;
    }

    private String botName = "IlyaPrishchepaTouristTelegramBot";

    private String botToken = "1735796290:AAHGWxx_7NDmu_I0pBHhljDX-GZ4Mp7jBgE";

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();

        if (message != null && update.getMessage().hasText()) {

            long chatId = message.getChatId();
            City reqCity = cityService.findByName(message.getText());
            String msg;

            if (reqCity != null) {
                msg = reqCity.getDescription();
            } else {
                msg = "There is no information for this city";
            }

            this.sendMessage(chatId, msg);
        }
    }

    private void sendMessage(long chatId, String msg) {
        try {
            execute(new SendMessage(chatId, msg));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
