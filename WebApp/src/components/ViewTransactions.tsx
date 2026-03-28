import { useAppSelector } from "../store/hooks";
import { selectTransactions } from "../store/transactionSlice";
import type { Transaction } from "../types";
import ViewTransaction from "./ViewTransaction";

function ViewTransactions() {
	const transactions: Transaction[] = useAppSelector(selectTransactions);

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

