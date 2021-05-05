package by.tourist.bot;

import by.tourist.services.implementation.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;

@Component
public class StartTelegramBot {

    @Autowired
    private CityServiceImpl cityService;

    @PostConstruct
    public void run() {
        try {
            ApiContextInitializer.init();
            TelegramBotsApi telegram = new TelegramBotsApi();
            telegram.registerBot(new TelegramBot(cityService));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
