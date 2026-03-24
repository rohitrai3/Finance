import ViewTransaction from "./ViewTransaction";
import { useGetTransactionsQuery } from "../store/transactionSlice";

function ViewTransactions() {
	const { data, isError, isLoading, isSuccess } = useGetTransactionsQuery("get");

	if (isError) {

		return <div>Error</div>;
	}

	if (isLoading) {

		return <div>Loading...</div>;
	}

	if (isSuccess) {

		return <div className="flex flex-wrap gap-4 justify-between h-full overflow-auto">
			{data.transactions.map(transaction =>
				<ViewTransaction
					key={transaction.id}
					amount={transaction.amount}
					type={transaction.type}
					date={transaction.date}
					description={transaction.description}
				/>)}
		</div>;
	}
}

export default ViewTransactions;

