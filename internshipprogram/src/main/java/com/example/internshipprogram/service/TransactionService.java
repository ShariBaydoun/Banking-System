package com.example.internshipprogram.service;

import com.example.internshipprogram.DTO.TransactionRequestDTO;
import com.example.internshipprogram.DTO.TransactionResponseDTO;
import com.example.internshipprogram.Entity.Transaction;
import com.example.internshipprogram.enums.Currency;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public interface TransactionService {
    Transaction createTransaction( TransactionRequestDTO transactionRequestDTO);
    boolean BookTransaction(String cardNumber, BigDecimal amount);
     void processRefund(String cardNumber, BigDecimal amount);
}


