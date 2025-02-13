package com.example.internshipprogram.Mapper;

import com.example.internshipprogram.DTO.CardRequestDTO;
import com.example.internshipprogram.DTO.CardResponseDTO;
import com.example.internshipprogram.Entity.Card;

public class CardMapper {
    public static CardResponseDTO mapToCardResponseDTO(Card card) {
        return new CardResponseDTO(card.getId(), card.getStatus(), card.getExpiry(), card.getCardNumber());
    }

    public static Card mapCardRequestDTOToCard(CardRequestDTO cardRequestDTO) {
        Card card = new Card();
        card.setCardNumber(cardRequestDTO.getCardNumber());
        card.setExpiry(cardRequestDTO.getExpiry());
        card.setStatus(cardRequestDTO.getStatus());
        return card;
    }
}
