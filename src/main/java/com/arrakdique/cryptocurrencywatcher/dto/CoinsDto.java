package com.arrakdique.cryptocurrencywatcher.dto;

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
    private float price_usd;
}
