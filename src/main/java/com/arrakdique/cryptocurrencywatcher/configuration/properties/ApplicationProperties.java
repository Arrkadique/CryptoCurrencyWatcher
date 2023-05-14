package com.arrakdique.cryptocurrencywatcher.configuration.properties;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
    private float percentForComparison;
    private long requestTimeoutInSec;
    private int parallelism;
    private String host;
    private List<Long> coinsId;
}
