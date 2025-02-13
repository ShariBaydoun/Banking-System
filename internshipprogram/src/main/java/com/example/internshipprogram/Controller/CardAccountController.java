package com.example.internshipprogram.Controller;

import com.example.internshipprogram.DTO.CardAccountRequestDTO;
import com.example.internshipprogram.DTO.CardAccountResponseDTO;
import com.example.internshipprogram.Mapper.CardAccountMapper;
import com.example.internshipprogram.service.CardAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/CardAccount")
@RequiredArgsConstructor
public class CardAccountController {
private final CardAccountService cardAccountService;
@PostMapping
public ResponseEntity<CardAccountResponseDTO> createCardAccount(@RequestBody CardAccountRequestDTO cardAccountRequestDTO){
    return new ResponseEntity<>(CardAccountMapper.toResponseDTO(cardAccountService.createCardAccount(cardAccountRequestDTO)),
            HttpStatus.CREATED);
}



}
