package com.example.internshipprogram.DTO;

import com.example.internshipprogram.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CardResponseDTO {
    private UUID id;
    private Status status;
    private Date expiry;
    private String cardNumber;
}
