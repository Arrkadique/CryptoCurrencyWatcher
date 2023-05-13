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

@Service
@Slf4j
@RequiredArgsConstructor
public class CoinsService {
    private final CoinsRepository coinsRepository;
    private final RestTemplate restTemplate;
    public List<Coin> makeRequest(){
        List<Coin> coins = new ArrayList<>();
        ResponseEntity<CoinsDto[]> response;
        CoinsDto coinsDto;
        for (CoinsRequests url: CoinsRequests.values()) {
            response = restTemplate.getForEntity(url.getUrl(), CoinsDto[].class);
            coinsDto = Objects.requireNonNull(response.getBody())[0];
            coins.add(processResponse(coinsDto));
        }
        coinsRepository.saveAll(coins);

        log.info("Fetched successful");
        return coins;
    }

    public Coin processResponse(CoinsDto coinsDto){
        return new Coin(coinsDto.getId(), coinsDto.getSymbol(), coinsDto.getPrice_usd());
    }

}
