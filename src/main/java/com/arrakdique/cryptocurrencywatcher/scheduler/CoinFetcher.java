package com.arrakdique.cryptocurrencywatcher.scheduler;

import com.arrakdique.cryptocurrencywatcher.entity.Coin;
import com.arrakdique.cryptocurrencywatcher.entity.Users;
import com.arrakdique.cryptocurrencywatcher.service.CoinsService;
import com.arrakdique.cryptocurrencywatcher.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoinFetcher {

    private final UsersService usersService;
    private final CoinsService coinsService;

    @Scheduled(initialDelay = 3000, fixedRate = 60000)
    public void ExecuteRequest(){
        log.info("Start fetching data...");
        coinsService.makeRequest();
        priceChecker();
    }

    public void priceChecker(){
        usersService.getAllUsers().forEach(user ->
                checkPrice(user, coinsService.getCoinBySymbol(user.getSymbol())));
    }

    public void checkPrice(Users user, Coin coin) {
        double percentageToComparison = (user.getPrice() * 1) / 100;
        if(Math.abs(user.getPrice() - coin.getPrice()) > percentageToComparison){
            double priceChangePercentage = coin.getPrice() / user.getPrice() * 100 - 100;
            log.warn("id: {} username: {} percent: {}%", coin.getSymbol(), user.getUsername(),
                    Math.round(priceChangePercentage * 100.0) / 100.0);
        }
    }

}
