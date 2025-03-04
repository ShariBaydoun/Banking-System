package com.example.internshipprogram.Mapper;

import com.example.internshipprogram.DTO.TransactionRequestDTO;
import com.example.internshipprogram.DTO.TransactionResponseDTO;
import com.example.internshipprogram.Entity.Card;
import com.example.internshipprogram.Entity.Transaction;
import com.example.internshipprogram.Repository.CardRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


public class TransactionMapper {

    public static TransactionResponseDTO mapTransactionToTransactionResponseDTO(Transaction transaction){
        return new TransactionResponseDTO(transaction.getId(),transaction.getTransactionAmount()
                ,transaction.getTransactionDate(),transaction.getTransactionType());
    }
    public static Transaction mapTransactionRequestDTOToTransaction(TransactionRequestDTO transactionRequestDTO,Card card){
        Transaction transaction =new Transaction();
        transaction.setTransactionAmount(transactionRequestDTO.getTransactionAmount());
        transaction.setTransactionDate(transactionRequestDTO.getTransactionDate());
        transaction.setTransactionType(transactionRequestDTO.getTransactionType());
        transaction.setCard(card);
        return transaction;
    }

}
