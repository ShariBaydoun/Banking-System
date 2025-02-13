package com.example.internshipprogram.DTO;

import com.example.internshipprogram.enums.Currency;
import com.example.internshipprogram.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor

public class AccountResponseDTO {
    private UUID id;
    private Status status;
    private BigDecimal balance;
    private Currency currency;
}
