package com.example.internshipprogram.Controller;

import com.example.internshipprogram.DTO.TransactionRequestDTO;
import com.example.internshipprogram.DTO.TransactionResponseDTO;
import com.example.internshipprogram.Entity.Transaction;
import com.example.internshipprogram.Mapper.TransactionMapper;
import com.example.internshipprogram.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionServicee;
   // public TransactionController(TransactionService transactionService) {
     //   this.transactionService = transactionService;
    //}
    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        try {
            // Call the service method to create a transaction
            Transaction transaction = transactionServicee.createTransaction(transactionRequestDTO);
            return new ResponseEntity<>(TransactionMapper.mapTransactionToTransactionResponseDTO(transaction), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Return a bad request status with the error message
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/Book")
    public ResponseEntity<String> processBookTransaction(
            @RequestParam String cardNumber,
            @RequestParam BigDecimal amount) {

        try {
            boolean isTransactionSuccessful = transactionServicee.BookTransaction(cardNumber, amount);
            if (isTransactionSuccessful) {
                return ResponseEntity.ok("Transaction processed successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Failed to process the transaction.");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing transaction: " + e.getMessage());
        }
    }
    // Endpoint for processing refunds
    @PostMapping("/refund")
    public ResponseEntity<String> processRefund(@RequestParam String cardNumber,
                                                @RequestParam BigDecimal amount) {
        try {
            transactionServicee.processRefund(cardNumber, amount);
            return ResponseEntity.ok("Refund processed successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

}

