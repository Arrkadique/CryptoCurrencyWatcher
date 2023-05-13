package com.arrakdique.cryptocurrencywatcher.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "coins")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Coin {

    @Id
    private Long id;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private double price;
}
