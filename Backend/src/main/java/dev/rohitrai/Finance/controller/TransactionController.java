package dev.rohitrai.Finance.controller;

import dev.rohitrai.Finance.model.AddTransactionOutput;
import dev.rohitrai.Finance.model.GetAllTransactionsOutput;
import dev.rohitrai.Finance.model.Transaction;
import dev.rohitrai.Finance.service.TransactionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@CrossOrigin(origins = { "http://localhost:5173" })
@RequestMapping("/transaction/")
@RequiredArgsConstructor
@RestController
public class TransactionController {

    @NonNull
    private TransactionService transactionService;

    @PostMapping("add")
    public AddTransactionOutput addTransaction(@RequestBody @NonNull Transaction transaction) {

        return transactionService.addNewTransaction(transaction);
    }

    @GetMapping("get")
    public GetAllTransactionsOutput getAllTransactions() {

        return transactionService.getAllTransactions();
    }

    @PostMapping("import")
    public void importTransactions(@RequestParam("file") MultipartFile file) {

        transactionService.importTransactions(file);
    }

    @GetMapping("export")
    public ResponseEntity<StreamingResponseBody> exportTransactions() {

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/csv; charset=UTF-8"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=transactions.csv")
                .body(transactionService.exportTransactions());
    }

    @DeleteMapping("delete")
    public void deleteAllTransactions() {
        transactionService.deleteAllTransactions();
    }

}
