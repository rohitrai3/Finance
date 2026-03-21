package dev.rohitrai.Finance.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GetAllTransactionsOutput {
    Status status;
    List<Transaction> transactions;
}
