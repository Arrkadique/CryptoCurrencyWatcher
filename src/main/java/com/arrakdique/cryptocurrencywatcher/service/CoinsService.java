package com.arrakdique.cryptocurrencywatcher.service;

import com.arrakdique.cryptocurrencywatcher.configuration.properties.ApplicationProperties;
import com.arrakdique.cryptocurrencywatcher.dto.CoinsDto;
import com.arrakdique.cryptocurrencywatcher.entity.Coin;
import com.arrakdique.cryptocurrencywatcher.repository.CoinsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoinsService {
    private final CoinsRepository coinsRepository;
    private final RestTemplate restTemplate;
    private final ApplicationProperties properties;

    public List<CoinsDto> makeRequest() {
        List<CoinsDto> loadedCoins = new ArrayList<>();
        ExecutorService executorService = null;

        List<Callable<CoinsDto>> callables = properties.getCoinsId().stream()
                .map(coinId -> getCall(this::executeRequest, coinId))
                .toList();

        try {
            executorService = Executors.newFixedThreadPool(properties.getParallelism());
            List<Future<CoinsDto>> futures = executorService.invokeAll(
                    callables,
                    properties.getRequestTimeoutInSec(),
                    TimeUnit.SECONDS);

            loadedCoins = futures.stream()
                    .map(this::getFutureResult)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            if (executorService != null) {
                executorService.shutdown();
            }
            log.error("Failed to initiate future", e);
        }

        log.info("Fetched successful");
        return loadedCoins;
    }

    public Callable<CoinsDto> getCall(Function<Long, CoinsDto> call, Long coinId) {
        return () -> call.apply(coinId);
    }

    public CoinsDto getFutureResult(Future<CoinsDto> future) {
        if (future.isDone()) {
            try {
                return future.get();
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Failed to fetch stat. {}", e.getMessage());
            }
        }
        return null;
    }

    public CoinsDto executeRequest(Long id) {
        ResponseEntity<CoinsDto[]> response = restTemplate
                .getForEntity(properties.getHost() + id, CoinsDto[].class);
        return Objects.requireNonNull(response.getBody())[0];
    }

    @Transactional
    public void saveCoins(List<CoinsDto> resultCoins) {
        List<Coin> coins = resultCoins.stream()
                .map(this::getOrCreateCoins)
                .collect(Collectors.toList());
        coinsRepository.saveAll(coins);
    }

    public Coin getOrCreateCoins(CoinsDto coinsDto) {
        Coin coin = coinsRepository.findById(coinsDto.getId())
                .orElse(this.createNewCoin(coinsDto));
        coin.setPrice(coinsDto.getPriceUsd());
        return coin;
    }

    private Coin createNewCoin(CoinsDto coinsDto) {
        return Coin.builder()
                .id(coinsDto.getId())
                .symbol(coinsDto.getSymbol())
                .price(coinsDto.getPriceUsd())
                .build();
    }

    public Coin getCoinBySymbol(String symbol) {
        return coinsRepository.findBySymbol(symbol);
    }

    public List<Coin> getAllCoins() {
        return coinsRepository.findAll();
    }

}
