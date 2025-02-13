package com.example.internshipprogram.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
public class TransactionResponseDTO {
    private UUID id;
    private BigDecimal transactionAmount;
    private Timestamp transactionDate;
    private String transactionType;

}
