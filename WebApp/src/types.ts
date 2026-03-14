export const Type = {
	DEBIT: "DEBIT",
	CREDIT: "CREDIT"
} as const;

export type AddTransactionInput = {
	amount: number;
	type: string;
	description: string;
	tags: string;
	date: number;
}

