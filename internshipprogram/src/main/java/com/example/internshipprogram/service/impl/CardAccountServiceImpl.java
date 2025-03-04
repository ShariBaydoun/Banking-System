package com.example.internshipprogram.service.impl;

import com.example.internshipprogram.DTO.CardAccountRequestDTO;
import com.example.internshipprogram.Entity.Account;
import com.example.internshipprogram.Entity.Card;
import com.example.internshipprogram.Entity.CardAccount;
import com.example.internshipprogram.Mapper.CardAccountMapper;
import com.example.internshipprogram.Repository.AccountRepository;
import com.example.internshipprogram.Repository.CardAccountRepository;
import com.example.internshipprogram.Repository.CardRepository;
import com.example.internshipprogram.service.CardAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CardAccountServiceImpl implements CardAccountService {
    private final CardAccountRepository cardAccountRepository;
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;
    @Override
    public CardAccount createCardAccount(CardAccountRequestDTO cardAccountRequestDTO) {
        Optional<Account> account = accountRepository.findById(cardAccountRequestDTO.getAccountId());
        Optional<Card> card = cardRepository.findById(cardAccountRequestDTO.getCardId());
        if (account.isEmpty()) {
            throw new RuntimeException("account not found");
        }
        if (card.isEmpty()) {
throw new RuntimeException("card not found");
        }
        return cardAccountRepository.save(CardAccountMapper.toEntity(cardAccountRequestDTO,card.get(),account.get()));
    }
}
