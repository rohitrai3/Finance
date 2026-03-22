package dev.rohitrai.Finance.dao;

import dev.rohitrai.Finance.model.Transaction;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByOrderByDateDesc();
}
