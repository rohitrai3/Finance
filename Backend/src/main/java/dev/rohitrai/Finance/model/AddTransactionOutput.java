package dev.rohitrai.Finance.model;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddTransactionOutput {
	private Status status;
	private UUID id;
}
