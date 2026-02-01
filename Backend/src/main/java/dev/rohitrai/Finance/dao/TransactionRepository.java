package dev.rohitrai.Finance.dao;

import dev.rohitrai.Finance.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}
