package dev.rohitrai.Finance.dao;

import dev.rohitrai.Finance.model.Transaction;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

public interface TransactionRepository extends ListCrudRepository<Transaction, UUID> {
}
