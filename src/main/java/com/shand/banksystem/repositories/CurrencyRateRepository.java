package com.shand.banksystem.repositories;

import com.shand.banksystem.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {
    List<CurrencyRate> findAllByNameIn(List<String> names);
}
