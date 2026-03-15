package dev.rohitrai.Finance.service;

import dev.rohitrai.Finance.dao.TransactionRepository;
import dev.rohitrai.Finance.model.AddTransactionOutput;
import dev.rohitrai.Finance.model.Status;
import dev.rohitrai.Finance.model.Transaction;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionService {

    @NonNull
    private TransactionRepository transactionRepository;

    public AddTransactionOutput addNewTransaction(Transaction transaction) {
        Transaction response = transactionRepository.save(transaction);

        return AddTransactionOutput.builder()
                .status(Status.SUCCESS)
                .id(response.getId())
                .build();
    }

    public ResponseEntity<Iterable<Transaction>> getAllTransactions() {
        Iterable<Transaction> transactions = transactionRepository.findAll();

        return ResponseEntity.ok(transactions);
    }

}
