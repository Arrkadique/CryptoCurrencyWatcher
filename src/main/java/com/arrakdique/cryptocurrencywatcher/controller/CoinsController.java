package com.arrakdique.cryptocurrencywatcher.controller;

import com.arrakdique.cryptocurrencywatcher.service.CoinsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/coins")
public class CoinsController {
    private CoinsService coinsService;

    @Autowired
    public CoinsController(CoinsService coinsService) {
        this.coinsService = coinsService;
    }

    @GetMapping("/all")
    public String getAllCoins(){
        coinsService.makeRequest();
        return null;
    }

    @GetMapping("/{symbol}")
    public float getCoinPrice(@PathVariable String symbol){

        return 0F;
    }


}
