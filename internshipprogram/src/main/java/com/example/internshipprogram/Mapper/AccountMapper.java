package com.example.internshipprogram.Mapper;

import com.example.internshipprogram.DTO.AccountRequestDTO;
import com.example.internshipprogram.DTO.AccountResponseDTO;
import com.example.internshipprogram.Entity.Account;

public class AccountMapper {
    public static Account mapAccountRequestToAccount(AccountRequestDTO accountRequestDTO){
        Account account = new Account();
        account.setBalance(accountRequestDTO.getBalance());
        account.setStatus(accountRequestDTO.getStatus());
        account.setCurrency(accountRequestDTO.getCurrency());
        return account;
    }

    public static AccountResponseDTO mapToAccountResponseDTO(Account account){
        return new AccountResponseDTO(account.getId(),
                account.getStatus(),account.getBalance(),account.getCurrency());
    }
}
