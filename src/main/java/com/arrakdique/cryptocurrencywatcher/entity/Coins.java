package com.arrakdique.cryptocurrencywatcher.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coins")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coins {

    @Id
    private Long id;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private float price;
}
