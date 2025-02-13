package com.example.internshipprogram.Repository;

import com.example.internshipprogram.Entity.CardAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CardAccountRepository extends JpaRepository<CardAccount, UUID> {
    List<CardAccount> findByCardId(UUID cardId);
}
