package com.shand.banksystem.repositories;

import com.shand.banksystem.model.FinancialOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinancialOperationRepository extends JpaRepository<FinancialOperation, Long>,
        JpaSpecificationExecutor<FinancialOperation> {
}
