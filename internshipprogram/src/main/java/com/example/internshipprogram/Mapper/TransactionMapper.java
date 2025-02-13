package com.example.internshipprogram.Mapper;

import com.example.internshipprogram.DTO.TransactionResponseDTO;
import com.example.internshipprogram.Entity.Card;
import com.example.internshipprogram.Entity.Transaction;

public class TransactionMapper {
    public static Transaction mapToTransaction(TransactionResponseDTO transactionResponseDTO){
        Transaction transaction=new Transaction(transactionResponseDTO.getId(),
                transactionResponseDTO.getTransactionAmount(), transactionResponseDTO.getTransactionDate(),
                transactionResponseDTO.getTransactionType(),new Card());
        return transaction;
    }
    public static TransactionResponseDTO mapToTransactiondto(Transaction transaction){
        TransactionResponseDTO transactionResponseDTO =new TransactionResponseDTO(transaction.getId(),
                transaction.getTransactionAmount(),transaction.getTransactionDate(),
                transaction.getTransactionType());
        return transactionResponseDTO;
    }

}
