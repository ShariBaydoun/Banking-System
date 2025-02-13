package com.example.internshipprogram.Mapper;

import com.example.internshipprogram.Entity.CardAccount;
import com.example.internshipprogram.DTO.CardAccountRequestDTO;
import com.example.internshipprogram.DTO.CardAccountResponseDTO;
import com.example.internshipprogram.DTO.CardRequestDTO;
import com.example.internshipprogram.DTO.CardResponseDTO;
import com.example.internshipprogram.DTO.AccountRequestDTO;
import com.example.internshipprogram.DTO.AccountResponseDTO;
import com.example.internshipprogram.Entity.Card;
import com.example.internshipprogram.Entity.Account;

import java.util.UUID;

public class CardAccountMapper {

    // Method to map CardAccountRequestDTO to CardAccount entity
    public static CardAccount toEntity(CardAccountRequestDTO cardAccountRequestDTO, Card card, Account account) {
        CardAccount cardAccount = new CardAccount();
        cardAccount.setId(UUID.randomUUID()); // Generate a new UUID for CardAccount
        cardAccount.setCard(card); // Set the Card entity
        cardAccount.setAccount(account); // Set the Account entity
        return cardAccount;
    }

    // Method to map CardAccount entity to CardAccountResponseDTO
    public static CardAccountResponseDTO toResponseDTO(CardAccount cardAccount) {
        // Map Card and Account to their respective Response DTOs

        CardResponseDTO cardResponseDTO = new CardResponseDTO(
                cardAccount.getCard().getId(),
                cardAccount.getCard().getStatus(),
                cardAccount.getCard().getExpiry(),
                cardAccount.getCard().getCardNumber()
        );

        AccountResponseDTO accountResponseDTO = new AccountResponseDTO(
                cardAccount.getAccount().getId(),
                cardAccount.getAccount().getStatus(),
                cardAccount.getAccount().getBalance(),
                cardAccount.getAccount().getCurrency()
        );

        return new CardAccountResponseDTO(
                cardAccount.getId(),
                accountResponseDTO,
                cardResponseDTO,
                cardAccount.getStatus()
        );
    }
//
//    // Method to map CardAccountRequestDTO to CardAccount (without Card and Account objects)
//    public static CardAccount toEntity(CardAccountRequestDTO cardAccountRequestDTO) {
//        CardAccount cardAccount = new CardAccount();
//        cardAccount.setStatus(cardAccountRequestDTO.getStatus());
//        return cardAccount;
//    }
}
