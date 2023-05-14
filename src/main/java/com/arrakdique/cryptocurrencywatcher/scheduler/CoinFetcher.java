package com.arrakdique.cryptocurrencywatcher.scheduler;

import com.arrakdique.cryptocurrencywatcher.configuration.properties.ApplicationProperties;
import com.arrakdique.cryptocurrencywatcher.entity.Coin;
import com.arrakdique.cryptocurrencywatcher.entity.Users;
import com.arrakdique.cryptocurrencywatcher.service.CoinsService;
import com.arrakdique.cryptocurrencywatcher.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoinFetcher {

    private final UsersService usersService;
    private final CoinsService coinsService;
    private final ApplicationProperties properties;

    @Scheduled(initialDelayString = "${app.initialDelayInMillis}", fixedRateString = "${app.fixedRateInMillis}")
    public void ExecuteRequest() {
        log.info("Start fetching data...");
        execute();
        priceChecker();
    }

    public void execute() {
        coinsService.saveCoins(coinsService.makeRequest());
    }

    public void priceChecker() {
        Map<String, Coin> cache = new HashMap<>();
        usersService.getAllUsers().forEach(user -> {
            Coin coin = cache.get(user.getSymbol());
            if (Objects.isNull(coin)) {
                coinsService.getCoinBySymbol(user.getSymbol());
                cache.put(coin.getSymbol(), coin);
            }
            checkPrice(user, coin);
        });
    }

    public void checkPrice(Users user, Coin coin) {
        double percentageToComparison = (user.getPrice() * properties.getPercentForComparison()) / 100;
        if (Math.abs(user.getPrice() - coin.getPrice()) > percentageToComparison) {
            double priceChangePercentage = coin.getPrice() / user.getPrice() * 100 - 100;
            log.warn("id: {} username: {} percent: {}%", coin.getSymbol(), user.getUsername(),
                    Math.round(priceChangePercentage * 100.0) / 100.0);
        }
    }

}
