package com.shand.banksystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "currency_table")
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_table_seq")
    @SequenceGenerator(name = "currency_table_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "rate", nullable = false)
    private BigDecimal rate;

    public CurrencyRate(String name, BigDecimal rate) {
        this.name = name;
        this.rate = rate;
    }

    public CurrencyRate() {
    }

}
