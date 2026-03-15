package dev.rohitrai.Finance.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddTransactionOutput {
	private Status status;
	private int id;
}
