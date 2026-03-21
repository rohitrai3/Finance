export type ViewTransactionProps = {
	amount: number;
	type: string;
	date: number;
	description: string;
}

function ViewTransaction({
	amount,
	type,
	date,
	description
}: ViewTransactionProps) {
	function getDate() {
		const fullDate = new Date(date).toString();

		return `${fullDate.substring(8, 10)} ${fullDate.substring(4, 7)} ${fullDate.substring(11, 15)}`;
	}

	return <div className="border-2 border-gray p-4 rounded-2xl">
		<div className="flex">
			₹<span className="text-5xl flex-1">{amount}</span>
			<div className="pl-4 text-right">
				{type} <p>{getDate()}</p>
			</div>
		</div>
		<p>{description}</p>
	</div>;
}

export default ViewTransaction;

