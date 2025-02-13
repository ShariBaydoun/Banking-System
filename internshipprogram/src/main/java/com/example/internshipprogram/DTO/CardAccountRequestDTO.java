package com.example.internshipprogram.DTO;

import com.example.internshipprogram.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CardAccountRequestDTO {
    private UUID cardId;
    private UUID accountId;
    private Status status;

}
