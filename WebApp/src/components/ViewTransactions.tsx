import { useState } from "react";
import type { Transaction } from "../types";
import ViewTransaction from "./ViewTransaction";

let didInit = false;

function ViewTransactions() {
	const [transactions, setTransactions] = useState<Transaction[]>([]);

	if (!didInit) {
		fetch("http://localhost:8080/transaction/get")
			.then((res) => {
				return res.json();
			})
			.then((data) => {
				console.log("data: ", data);
				setTransactions(data);
			})
			.catch((err) => {
				console.log("Error: ", err);
			})
			.finally(() => {
				console.log("transactions: ", transactions);
				didInit = true;
			});
	}

	return <div className="flex flex-wrap gap-4 justify-between">
		{transactions.map(transaction =>
			<ViewTransaction
				key={transaction.id}
				amount={transaction.amount}
				type={transaction.type}
				date={transaction.date}
				description={transaction.description}
			/>)}
	</div>;
}

export default ViewTransactions;

