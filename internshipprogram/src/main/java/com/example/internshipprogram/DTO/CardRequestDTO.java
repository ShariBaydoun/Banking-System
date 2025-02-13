package com.example.internshipprogram.DTO;

import com.example.internshipprogram.enums.Status;
import lombok.Data;

import java.util.Date;

@Data
public class CardRequestDTO {
    private Status status;
    private Date expiry;
    private String cardNumber;
}
