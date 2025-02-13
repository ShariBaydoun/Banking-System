package com.example.internshipprogram.DTO;

import com.example.internshipprogram.enums.Currency;
import com.example.internshipprogram.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AccountRequestDTO {
    private Status status;
    private BigDecimal balance;
    private Currency currency;
}
