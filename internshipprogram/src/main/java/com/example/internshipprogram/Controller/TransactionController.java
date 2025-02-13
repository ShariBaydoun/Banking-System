package com.example.internshipprogram.Controller;

import com.example.internshipprogram.DTO.TransactionRequestDTO;
import com.example.internshipprogram.DTO.TransactionResponseDTO;
import com.example.internshipprogram.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO transactionRequest) {
        try {
            // Call the service method to create a transaction
            TransactionResponseDTO transaction = transactionService.createTransaction(
                    transactionRequest.getCardId(),
                    transactionRequest.getTransactionAmount(),
                    transactionRequest.getTransactionType(),
                    transactionRequest.getTransactionDate(),
                    transactionRequest.getTransactionId(),
                    transactionRequest.getCurrency());
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Return a bad request status with the error message
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}

