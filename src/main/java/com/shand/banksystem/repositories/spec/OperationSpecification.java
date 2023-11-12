package com.shand.banksystem.repositories.spec;

import com.shand.banksystem.dto.OperationFilter;
import com.shand.banksystem.model.FinancialOperation;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OperationSpecification {
    private OperationSpecification() {
    }

    public static Specification<FinancialOperation> findByFilter(OperationFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(cb.between(root.get("dateTime"), filter.getStart(), filter.getEnd()));
            return cb.and(predicateList.toArray(predicateList.toArray(new Predicate[0])));
        };
    }
}
