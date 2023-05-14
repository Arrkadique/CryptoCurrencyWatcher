package com.arrakdique.cryptocurrencywatcher.configuration;

import com.arrakdique.cryptocurrencywatcher.configuration.properties.ApplicationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties({
        ApplicationProperties.class
})
public class ApplicationConfiguration {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
