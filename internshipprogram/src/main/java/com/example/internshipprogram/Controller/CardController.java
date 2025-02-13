package com.example.internshipprogram.Controller;

import com.example.internshipprogram.DTO.CardRequestDTO;
import com.example.internshipprogram.DTO.CardResponseDTO;
import com.example.internshipprogram.Entity.Card;
import com.example.internshipprogram.Mapper.CardMapper;
import com.example.internshipprogram.service.CardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/Card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping
    public ResponseEntity<CardResponseDTO> createCard(@RequestBody CardRequestDTO cardRequestDTO) {
        return new ResponseEntity<>(CardMapper.mapToCardResponseDTO(cardService.createCard(cardRequestDTO)), HttpStatus.CREATED);
    }

    @PostMapping("/activate/{id}")
    public ResponseEntity<String> activateCard(@PathVariable("id") UUID id) {
        // the id is treated as the identifier for the specific card,
        // so it's in the URL path and annotated with @PathVariable.
        //The status is the data you’re updating on the card,
        // so it’s part of the request body and annotated with @RequestBody.
        cardService.activateCard(id);

        return ResponseEntity.ok("Activated");
    }

    @PostMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivateCard(@PathVariable("id") UUID id) {
        cardService.deactivateCard(id);
        return ResponseEntity.ok("Deactivated");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardResponseDTO> getCard(@PathVariable UUID id) {
        return new ResponseEntity<>(CardMapper.mapToCardResponseDTO(cardService.retrieveCardDetails(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardResponseDTO> updateCard(@PathVariable UUID id, @RequestBody CardRequestDTO cardRequestDTO) {
        cardService.updateCard(id, cardRequestDTO);
        return new ResponseEntity<>(CardMapper.mapToCardResponseDTO(cardService.updateCard(id, cardRequestDTO)), HttpStatus.OK);

    }
}
