package dev.rohitrai.Finance.controller;

import dev.rohitrai.Finance.model.Transaction;
import dev.rohitrai.Finance.service.TransactionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/transaction")
@RequiredArgsConstructor
@RestController
public class TransactionController {

    @NonNull
    private TransactionService transactionService;

    @PostMapping("/add")
    public ResponseEntity<String> addTransaction(@RequestBody @NonNull Transaction transaction) {

        return transactionService.addNewTransaction(transaction);
    }

    @GetMapping("/get")
    public ResponseEntity<Iterable<Transaction>> getAllTransactions() {

        return transactionService.getAllTransactions();
    }

}
