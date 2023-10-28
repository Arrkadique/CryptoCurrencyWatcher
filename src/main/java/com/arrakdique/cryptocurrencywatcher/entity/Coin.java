package com.arrakdique.cryptocurrencywatcher.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "coins")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Slf4j
public class Coin extends BaseEntity{

    @Id
    private Long id;

    @PostUpdate 
    public void postPersist(){
        log.warn("Coin added!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

}
