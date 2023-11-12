package com.shand.banksystem.repositories;

import com.shand.banksystem.model.FinancialOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialOperationRepository extends JpaRepository<FinancialOperation, Long> {
}
