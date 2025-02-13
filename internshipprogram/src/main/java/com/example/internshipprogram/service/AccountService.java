package com.example.internshipprogram.service;

import com.example.internshipprogram.DTO.AccountRequestDTO;
import com.example.internshipprogram.Entity.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account createaccount(AccountRequestDTO accountRequestDTO);

    Account getAccountById(UUID id);

    Account deposit(UUID id, BigDecimal amount);

    Account withdraw(UUID id,BigDecimal amount);
    List<Account> getAllAccounts();
    void deleteAccount(UUID id);
    Account updateAccount(UUID id, AccountRequestDTO accountRequestDTO);
}
