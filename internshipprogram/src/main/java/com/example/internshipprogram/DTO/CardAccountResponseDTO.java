package com.example.internshipprogram.DTO;

import com.example.internshipprogram.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@AllArgsConstructor
@Getter
@Setter
public class CardAccountResponseDTO {
    private UUID id;
   private AccountResponseDTO accountResponseDTO;
   private CardResponseDTO cardResponseDTO;
   private Status status;

}
