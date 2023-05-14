package com.arrakdique.cryptocurrencywatcher.controller;

import com.arrakdique.cryptocurrencywatcher.entity.Coin;
import com.arrakdique.cryptocurrencywatcher.service.CoinsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CoinsController {
    private final CoinsService coinsService;

    @GetMapping("/all")
    public List<Coin> getAllCoins(){
        return coinsService.getAllCoins();
    }

    @GetMapping("/{symbol}")
    public double getCoinPrice(@PathVariable String symbol){
        return coinsService.getCoinBySymbol(symbol).getPrice();
    }


}
