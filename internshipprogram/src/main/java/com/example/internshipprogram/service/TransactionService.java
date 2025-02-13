package com.example.internshipprogram.service;

import com.example.internshipprogram.DTO.TransactionResponseDTO;
import com.example.internshipprogram.enums.Currency;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public interface TransactionService {
    TransactionResponseDTO createTransaction(UUID cardId, BigDecimal transactionAmount, String transactionType, Timestamp transactionDate, UUID transactionId, Currency currency);
}


