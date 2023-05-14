package com.arrakdique.cryptocurrencywatcher.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "coins")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Coin extends BaseEntity{

    @Id
    private Long id;

}
