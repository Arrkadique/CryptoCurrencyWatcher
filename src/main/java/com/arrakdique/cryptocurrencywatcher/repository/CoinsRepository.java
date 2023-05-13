package com.arrakdique.cryptocurrencywatcher.repository;

import com.arrakdique.cryptocurrencywatcher.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinsRepository extends JpaRepository<Coin, Long> {
    Coin findBySymbol(String symbol);
}
