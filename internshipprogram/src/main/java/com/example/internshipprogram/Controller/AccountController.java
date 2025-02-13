package com.example.internshipprogram.Controller;

import com.example.internshipprogram.DTO.AccountRequestDTO;
import com.example.internshipprogram.DTO.AccountResponseDTO;
import com.example.internshipprogram.Entity.Account;
import com.example.internshipprogram.Mapper.AccountMapper;
import com.example.internshipprogram.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
  private final AccountService accountService;
    //Add account REST API
    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@Valid @RequestBody AccountRequestDTO accountRequestDTO) {
        return new ResponseEntity<>(AccountMapper.mapToAccountResponseDTO(accountService.createaccount(accountRequestDTO)),
                HttpStatus.CREATED);
    }
    // Get account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getAccountbyID(@PathVariable UUID id){
        Account account = accountService.getAccountById(id);
        return new ResponseEntity<>(AccountMapper.mapToAccountResponseDTO(account),HttpStatus.FOUND);
    }

    //Get All Accounts REST API
    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        List<AccountResponseDTO> response = new ArrayList<>();
        accounts.forEach(account -> response.add(AccountMapper.mapToAccountResponseDTO(account)));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // Delete Account REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable UUID id){
        accountService.deleteAccount(id);
        return  ResponseEntity.ok("Account deleted");
    }
    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> updateAccount(@PathVariable UUID id,@RequestBody AccountRequestDTO accountRequestDTO){
        return new ResponseEntity<>(AccountMapper.mapToAccountResponseDTO(accountService.updateAccount(id,accountRequestDTO)),HttpStatus.OK);
    }

}

