package dev.rohitrai.Finance.service;

import dev.rohitrai.Finance.dao.TransactionRepository;
import dev.rohitrai.Finance.model.Transaction;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    @NonNull
    private TransactionRepository transactionRepository;

    public ResponseEntity<String> addNewTransaction(Transaction transaction) {
        transactionRepository.save(transaction);

        return ResponseEntity.ok("success");
    }

    public ResponseEntity<Iterable<Transaction>> getAllTransactions() {
        Iterable<Transaction> transactions = transactionRepository.findAll();

        return ResponseEntity.ok(transactions);
    }

}
