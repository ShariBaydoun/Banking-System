package com.example.internshipprogram.service.impl;

import com.example.internshipprogram.DTO.TransactionRequestDTO;
import com.example.internshipprogram.DTO.TransactionResponseDTO;
import com.example.internshipprogram.Entity.Account;
import com.example.internshipprogram.Entity.Card;
import com.example.internshipprogram.Entity.CardAccount;
import com.example.internshipprogram.Entity.Transaction;
import com.example.internshipprogram.Mapper.CardMapper;
import com.example.internshipprogram.Mapper.TransactionMapper;
import com.example.internshipprogram.Repository.AccountRepository;
import com.example.internshipprogram.Repository.CardAccountRepository;
import com.example.internshipprogram.Repository.CardRepository;
import com.example.internshipprogram.Repository.TransactionRepository;
import com.example.internshipprogram.enums.Currency;
import com.example.internshipprogram.enums.Status;
import com.example.internshipprogram.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.example.internshipprogram.enums.Currency.USD;
import static java.lang.String.valueOf;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;
    private final TransactionRepository transactionRepository;
    private final CardAccountRepository cardAccountRepository;


    @Override
    public Transaction createTransaction( TransactionRequestDTO transactionRequestDTO) {
        UUID cardId=transactionRequestDTO.getCardId();
        Card card=cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        if (!card.getStatus().equals(Status.ACTIVE)) {
            throw new RuntimeException("Card is not active");
        }
        if (card.getExpiry().before(new Date())) {
            throw new RuntimeException("Card is expired");
        }

        // Find accounts linked to the card
        List<CardAccount> cardAccounts = cardAccountRepository.findByCardId(cardId);
        if (cardAccounts.isEmpty()) {
            throw new RuntimeException("No accounts linked to this card");
        }

        // Get currency from transactionRequestDTO
        Currency currency = transactionRequestDTO.getCurrency();

        // Find the right account based on the currency
        Account selectedAccount = null;
        for (CardAccount cardAccount : cardAccounts) {
            Account account = cardAccount.getAccount();
            if (account.getCurrency().equals(currency) && account.getStatus().equals(Status.ACTIVE)) {
                selectedAccount = account;
                break; // Stop once we find the matching currency
            }
        }

        if (selectedAccount == null) {
            throw new RuntimeException("No matching currency account for this card");
        }
        String transactionType = transactionRequestDTO.getTransactionType();
        BigDecimal transactionAmount = transactionRequestDTO.getTransactionAmount();

        if (transactionRequestDTO.getTransactionType().equalsIgnoreCase("C")) {
            creditTransaction(selectedAccount, transactionAmount);
        } else if (transactionRequestDTO.getTransactionType().equalsIgnoreCase("D")) {
            debitTransaction(selectedAccount, transactionAmount);
        } else {
            throw new RuntimeException("Invalid transaction type. Use 'C' for credit and 'D' for debit.");
        }
        return transactionRepository.
                save(TransactionMapper.mapTransactionRequestDTOToTransaction(transactionRequestDTO,card));
    }

    private void creditTransaction(Account account, BigDecimal transactionAmount) {
        account.setBalance(account.getBalance().add(transactionAmount));
    }

    private void debitTransaction(Account account, BigDecimal transactionAmount) {
        if (account.getBalance().compareTo(transactionAmount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance().subtract(transactionAmount));
    }


    public  boolean BookTransaction(String cardNumber, BigDecimal amount) {
        // Find the card by cardNumber
        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        // Get the linked account(s) for this card
        List<CardAccount> cardAccounts = cardAccountRepository.findByCard_CardNumber(cardNumber);
        if (cardAccounts.isEmpty()) {
            throw new RuntimeException("No accounts linked to this card");
        }
        UUID cardId=card.getId();
        TransactionRequestDTO transactionRequestDTO=new TransactionRequestDTO(cardId,amount,
                "D",Timestamp.valueOf(LocalDateTime.now()),USD);
         createTransaction(transactionRequestDTO);

                return true; // Sufficient funds available
            }

    public void processRefund(String cardNumber, BigDecimal amount) {
        // Find the card linked to the given card number
        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        // Ensure the card is active before proceeding
        if (!card.getStatus().equals(Status.ACTIVE)) {
            throw new RuntimeException("Card is inactive. Refund cannot be processed.");
        }

        // Create a transaction request DTO for refund (Credit transaction)
        TransactionRequestDTO refundTransaction = new TransactionRequestDTO(
                card.getId(), amount, "C", Timestamp.valueOf(LocalDateTime.now()), USD
        );

        // Call createTransaction to process the refund
        createTransaction(refundTransaction);
    }


}








