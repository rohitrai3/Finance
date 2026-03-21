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
				setTransactions(data.transactions);
			})
			.catch((err) => {
				console.log("Error: ", err);
			})
			.finally(() => {
				didInit = true;
			});
	}

	return <div className="flex flex-wrap gap-4 justify-between h-full overflow-auto">
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

