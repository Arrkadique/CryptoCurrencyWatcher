package com.arrakdique.cryptocurrencywatcher.service;

import com.arrakdique.cryptocurrencywatcher.dto.CoinsDto;
import com.arrakdique.cryptocurrencywatcher.dto.CoinsRequests;
import com.arrakdique.cryptocurrencywatcher.entity.Coin;
import com.arrakdique.cryptocurrencywatcher.repository.CoinsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoinsService {
    private final CoinsRepository coinsRepository;
    private final RestTemplate restTemplate;
    public List<Coin> makeRequest(){
        List<CoinsDto> coinsDto = new ArrayList<>();
        ResponseEntity<CoinsDto[]> response;
        for (CoinsRequests url: CoinsRequests.values()) {
            response = restTemplate.getForEntity(url.getUrl(), CoinsDto[].class);
            coinsDto.add(Objects.requireNonNull(response.getBody())[0]);
        }

        log.info("Fetched successful");
        return saveCoins(coinsDto);
    }

    public List<Coin> saveCoins(List<CoinsDto> resultCoins){
        List<Coin> coins = resultCoins.stream()
                .map(this::getOrCreateCoins)
                .collect(Collectors.toList());
        return coinsRepository.saveAll(coins);
    }

    public Coin getOrCreateCoins(CoinsDto coinsDto){
        Coin coin = coinsRepository.findById(coinsDto.getId()).orElse(this.createNewCoin(coinsDto));
        coin.setPrice(coinsDto.getPrice_usd());
        return coin;
    }

    private Coin createNewCoin(CoinsDto coinsDto) {
        return Coin.builder()
                .id(coinsDto.getId())
                .symbol(coinsDto.getSymbol())
                .price(coinsDto.getPrice_usd())
                .build();
    }

    public Coin getCoinBySymbol(String symbol){
        return coinsRepository.findBySymbol(symbol);
    }

    public List<Coin> getAllCoins(){
        return coinsRepository.findAll();
    }

}
