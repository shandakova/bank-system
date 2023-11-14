package com.shand.banksystem.model;

import com.shand.banksystem.model.enums.FinancialOperationType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;


@Data
@Entity
@Table(name = "financial_operation")
public class FinancialOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fin_operation_seq")
    @SequenceGenerator(name = "fin_operation_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private FinancialOperationType type;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "date", nullable = false)
    private ZonedDateTime dateTime;
}
