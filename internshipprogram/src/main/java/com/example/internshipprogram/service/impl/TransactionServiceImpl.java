package com.example.internshipprogram.service.impl;

import com.example.internshipprogram.DTO.TransactionResponseDTO;
import com.example.internshipprogram.Entity.Account;
import com.example.internshipprogram.Entity.Card;
import com.example.internshipprogram.Entity.CardAccount;
import com.example.internshipprogram.Entity.Transaction;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;
    private final TransactionRepository transactionRepository;
    private final CardAccountRepository cardAccountRepository;

    @Override
    public TransactionResponseDTO createTransaction(UUID cardId, BigDecimal transactionAmount, String transactionType, Timestamp transactionDate, UUID transactionId, Currency currency){

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        if (!card.getStatus().equals(Status.ACTIVE)){
            throw new RuntimeException("Card is not active");
        }
        if(card.getExpiry().before(new Date())){
            throw new RuntimeException("Card is expired");
        }

        // find accounts linked to the card
        List<CardAccount> cardAccounts= cardAccountRepository.findByCardId(cardId);
        if (cardAccounts.isEmpty()){
            throw new RuntimeException("no accounts linked to this card");
        }
        //Find the right account based on the currency
        Account selectedAccount=null;
        for(CardAccount cardAccount : cardAccounts){
            Account account = cardAccount.getAccount();
            if(account.getCurrency() == currency && account.getStatus().equals(Status.ACTIVE))
            {
                selectedAccount=account;
                break;//stop once we find the matching currency
            }
        }
        if (selectedAccount==null){
            throw new RuntimeException("No matching currency account for this card");
        }
        // Process the transaction based on the type
        if (transactionType.equalsIgnoreCase("C")) {
            creditTransaction(selectedAccount, transactionAmount);
        } else if (transactionType.equalsIgnoreCase("D")) {
            debitTransaction(selectedAccount, transactionAmount);
        } else {
            throw new RuntimeException("Invalid transaction type. Use 'C' for credit and 'D' for debit.");
        }
        // Create and save the transaction
        Transaction transaction = new Transaction(transactionId, transactionAmount, transactionDate, transactionType,card);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return TransactionMapper.mapToTransactiondto(savedTransaction);
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
}
