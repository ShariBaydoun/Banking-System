package com.example.internshipprogram.Entity;

import com.example.internshipprogram.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "card_account")
@Getter
@Setter
public class CardAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Auto-generate the ID column
    @Column(name = "id")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "card_id")  // Foreign key to the Card entity
    private Card card;

    @ManyToOne
    @JoinColumn(name = "account_id")  // Foreign key to the Account entity
    private Account account;

    @Column(name = "status")
    private Status status;

}
