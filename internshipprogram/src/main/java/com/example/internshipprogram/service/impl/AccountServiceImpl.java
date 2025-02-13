package com.example.internshipprogram.service.impl;

import com.example.internshipprogram.DTO.AccountRequestDTO;
import com.example.internshipprogram.Entity.Account;
import com.example.internshipprogram.Mapper.AccountMapper;
import com.example.internshipprogram.Repository.AccountRepository;
import com.example.internshipprogram.enums.Status;
import com.example.internshipprogram.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account createaccount(AccountRequestDTO accountRequestDTO) {
        return accountRepository.save(AccountMapper.mapAccountRequestToAccount(accountRequestDTO));
    }

    @Override
    public Account getAccountById(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public Account deposit(UUID id, BigDecimal amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (!account.getStatus().equals(Status.ACTIVE)) {
            throw new RuntimeException("Account is inactive. Cannot perform transaction.");
        }

        BigDecimal total = account.getBalance().add(amount);
        account.setBalance(total);
        return accountRepository.save(account);
    }

    @Override
    public Account withdraw(UUID id, BigDecimal amount) {
        Account account = accountRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Account not found"));
        if (!account.getStatus().equals(Status.ACTIVE)) {
            throw new RuntimeException("Account is inactive. Cannot perform transaction.");
        }

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        BigDecimal total = account.getBalance().subtract(amount);
        account.setBalance(total);
        return accountRepository.save(account);
    }


    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteAccount(UUID id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepository.delete(account);
    }

    @Override
    public Account updateAccount(UUID id, AccountRequestDTO accountRequestDTO) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(accountRequestDTO.getBalance());
        account.setStatus(accountRequestDTO.getStatus());
        return accountRepository.save(account);
    }


}

