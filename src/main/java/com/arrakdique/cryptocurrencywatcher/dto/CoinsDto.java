package com.arrakdique.cryptocurrencywatcher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoinsDto {
    private Long id;
    private String symbol;
    @JsonProperty("price_usd")
    private float priceUsd;
}
