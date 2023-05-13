package com.arrakdique.cryptocurrencywatcher.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CoinsRequests {
    BTC("https://api.coinlore.net/api/ticker/?id=90"),
    ETH("https://api.coinlore.net/api/ticker/?id=80"),
    SOL("https://api.coinlore.net/api/ticker/?id=48543");

    private final String url;
}
