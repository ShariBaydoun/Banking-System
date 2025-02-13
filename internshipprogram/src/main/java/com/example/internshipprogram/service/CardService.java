package com.example.internshipprogram.service;

import com.example.internshipprogram.DTO.CardRequestDTO;
import com.example.internshipprogram.DTO.CardResponseDTO;
import com.example.internshipprogram.Entity.Card;

import java.util.UUID;

public interface CardService {
    Card createCard(CardRequestDTO cardRequestDTO);
    void activateCard(UUID id);
    void deactivateCard(UUID id);
    Card retrieveCardDetails(UUID id);
    Card updateCard(UUID id, CardRequestDTO cardRequestDTO);
}
