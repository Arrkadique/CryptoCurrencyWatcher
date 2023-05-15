package com.arrakdique.cryptocurrencywatcher.controller;

import com.arrakdique.cryptocurrencywatcher.dto.CoinsResponseDto;
import com.arrakdique.cryptocurrencywatcher.entity.Coin;
import com.arrakdique.cryptocurrencywatcher.entity.Users;
import com.arrakdique.cryptocurrencywatcher.service.CoinsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CoinsController {
    private final CoinsService coinsService;

    @Operation(summary = "Get all coins info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All coins info",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Users.class)) })})
    @GetMapping("/all")
    public List<Coin> getAllCoins(){
        return coinsService.getAllCoins();
    }

    @Operation(summary = "Get coin price by symbol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coin price by symbol",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Users.class)) })})
    @GetMapping("/{symbol}")
    public CoinsResponseDto getCoinPrice(@PathVariable String symbol){
        return new CoinsResponseDto(coinsService.getCoinBySymbol(symbol).getPrice());
    }


}
