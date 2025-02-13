package com.example.internshipprogram.Entity;

import com.example.internshipprogram.enums.Currency;
import com.example.internshipprogram.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name="accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "balance")
    private BigDecimal balance;
    @OneToMany(mappedBy = "account")
    private List<CardAccount> cardAccounts;
    @Enumerated(EnumType.STRING)
    @Column(name="currency")
    private Currency currency;
}
