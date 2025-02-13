package com.example.internshipprogram.service;

import com.example.internshipprogram.DTO.CardAccountRequestDTO;
import com.example.internshipprogram.Entity.Account;
import com.example.internshipprogram.Entity.Card;
import com.example.internshipprogram.Entity.CardAccount;

public interface CardAccountService {
    CardAccount createCardAccount(CardAccountRequestDTO cardAccountRequestDTO);
}
