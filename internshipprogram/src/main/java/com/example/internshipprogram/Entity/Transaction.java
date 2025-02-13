package com.example.internshipprogram.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @Column(name = "transaction_amount")
    private BigDecimal transactionAmount;
    @Column(name = "transaction_date")
    private Timestamp transactionDate;
    @Column(name = "transaction_type")
    private String transactionType;
    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)  // Foreign key to Card
    private Card card;
}

