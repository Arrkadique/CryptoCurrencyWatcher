package com.arrakdique.cryptocurrencywatcher.controller;

import com.arrakdique.cryptocurrencywatcher.entity.Coin;
import com.arrakdique.cryptocurrencywatcher.service.CoinsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController()
public class CoinsController {
    private CoinsService coinsService;

    @GetMapping("/all")
    public List<Coin> getAllCoins(){
        return coinsService.getAllCoins();
    }

    @GetMapping("/{symbol}")
    public Coin getCoinPrice(@PathVariable String symbol){
        return coinsService.getCoinBySymbol(symbol);
    }


}
