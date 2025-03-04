package com.example.internshipprogram.service.impl;

import com.example.internshipprogram.DTO.CardRequestDTO;
import com.example.internshipprogram.Entity.Card;
import com.example.internshipprogram.Mapper.CardMapper;
import com.example.internshipprogram.Repository.CardRepository;
import com.example.internshipprogram.enums.Status;
import com.example.internshipprogram.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.internshipprogram.enums.Status.ACTIVE;
import static com.example.internshipprogram.enums.Status.INACTIVE;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Override
    public Card createCard(CardRequestDTO cardRequestDTO) {
        return cardRepository.save(CardMapper.mapCardRequestDTOToCard(cardRequestDTO));
    }

    @Override
    public void activateCard(UUID id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        if (card.getStatus().equals(Status.INACTIVE)) {
            card.setStatus(ACTIVE);
            cardRepository.save(card);
        } else {
            throw new RuntimeException("Your card is already active!");
        }
    }

    @Override
    public void deactivateCard(UUID id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        if (!card.getStatus().equals(Status.INACTIVE)) {
            card.setStatus(INACTIVE);
            cardRepository.save(card);
        } else {
            throw new RuntimeException("Your card is already inactive!");
        }
    }

    @Override
    public Card retrieveCardDetails(UUID id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found"));
    }

    @Override
    public Card updateCard(UUID id, CardRequestDTO cardRequestDTO) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        card.setCardNumber(cardRequestDTO.getCardNumber());
        card.setStatus(cardRequestDTO.getStatus());
        card.setExpiry(cardRequestDTO.getExpiry());
        return cardRepository.save(card);
    }
}
