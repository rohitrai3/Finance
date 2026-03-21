package dev.rohitrai.Finance.service;

import dev.rohitrai.Finance.dao.TransactionRepository;
import dev.rohitrai.Finance.model.AddTransactionOutput;
import dev.rohitrai.Finance.model.GetAllTransactionsOutput;
import dev.rohitrai.Finance.model.Status;
import dev.rohitrai.Finance.model.Transaction;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@RequiredArgsConstructor
@Service
public class TransactionService {

    @NonNull
    private TransactionRepository transactionRepository;

    public AddTransactionOutput addNewTransaction(Transaction transaction) {
        transaction.setId(UUID.randomUUID());

        Transaction response = transactionRepository.save(transaction);

        return AddTransactionOutput.builder()
                .status(Status.SUCCESS)
                .id(response.getId())
                .build();
    }

    public GetAllTransactionsOutput getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();

        return GetAllTransactionsOutput.builder()
                .status(Status.SUCCESS)
                .transactions(transactions)
                .build();
    }

    public void importTransactions(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            List<Transaction> transactions = new CsvToBeanBuilder<Transaction>(reader)
                    .withType(Transaction.class)
                    .build()
                    .parse();

            transactions.forEach(transaction -> {
                addNewTransaction(transaction);
            });
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }
    }

    public StreamingResponseBody exportTransactions() {
        return outputStream -> {
            List<Transaction> transactions = transactionRepository.findAll();

            try (Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
                new StatefulBeanToCsvBuilder<Transaction>(writer).build().write(transactions);
            } catch (Exception ex) {
                System.out.println("Exception: " + ex);
            }
        };
    }

}
