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
};

export type Transaction = {
	id: string;
	amount: number;
	type: string;
	description: string;
	tags: string;
	date: number;
	createTime: number;
	updateTime: number;
};

export type GetTransactionsResponse = {
	transactions: Transaction[];
	status: string;
};

export type DashboardProps = {
	width: number;
	height: number;
};

