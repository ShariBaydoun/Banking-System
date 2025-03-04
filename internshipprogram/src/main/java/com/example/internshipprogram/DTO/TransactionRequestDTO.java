package com.example.internshipprogram.DTO;

import com.example.internshipprogram.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;
@Data
@AllArgsConstructor
public class TransactionRequestDTO {
        private UUID cardId;
        private BigDecimal transactionAmount;
        private String transactionType;
        private Timestamp transactionDate;
        private Currency currency;
    }
