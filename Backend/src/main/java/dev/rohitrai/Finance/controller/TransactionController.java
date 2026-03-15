package dev.rohitrai.Finance.controller;

import dev.rohitrai.Finance.model.AddTransactionOutput;
import dev.rohitrai.Finance.model.Transaction;
import dev.rohitrai.Finance.service.TransactionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:5173" })
@RequestMapping("/transaction")
@RequiredArgsConstructor
@RestController
public class TransactionController {

    @NonNull
    private TransactionService transactionService;

    @PostMapping("/add")
    public AddTransactionOutput addTransaction(@RequestBody @NonNull Transaction transaction) {

        return transactionService.addNewTransaction(transaction);
    }

    @GetMapping("/get")
    public ResponseEntity<Iterable<Transaction>> getAllTransactions() {

        return transactionService.getAllTransactions();
    }

}
