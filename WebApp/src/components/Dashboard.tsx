import { useState } from "react";
import { useGetTransactionsQuery } from "../store/transactionSlice";
import { Area, AreaChart, Tooltip, XAxis, YAxis, type TooltipContentProps } from "recharts";
import type { Transaction } from "../types";

function Dashboard() {
  const { data, isLoading, isError, isSuccess } = useGetTransactionsQuery("get");
  const [year, setYear] = useState<number>(new Date().getFullYear());
  const [month, setMonth] = useState<number>(new Date().getMonth());
  const years = [2026, 2025, 2024];
  const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
  let totalAmount = 0;

  const toolTip = ({ active, payload, label }: TooltipContentProps) => {
    const isVisible = active && payload && payload.length;

    return <div>{isVisible && `${label}: ${payload[0].value}`}</div>;
  }

  function getXAxisLabel(transaction: Transaction): string {
    const fullDate = new Date(transaction.date).toString();

    return fullDate.substring(4, 10);
  }

  if (isLoading) {

    return <div>Loading...</div>;
  }

  if (isError) {

    return <div>Error</div>;
  }

  if (isSuccess) {
    const debitTransactions = data.transactions.filter(transaction =>
      transaction.type === "DEBIT" &&
      new Date(transaction.date).getFullYear() === year &&
      new Date(transaction.date).getMonth() === month);

    debitTransactions.forEach(transaction => totalAmount = totalAmount + transaction.amount);

    return <div>
      <div className="flex justify-between">
        <p>Debit transactions: ₹{totalAmount}</p>
        <div className="flex gap-2 items-center">
          <label>Year:
            <select
              className="border border-white px-4 py-2 rounded-xl"
              value={year}
              onChange={(event) => { setYear(parseInt(event.target.value)) }}
            >
              {years.map((year, i) => <option value={year} key={i}>{year}</option>)}
            </select>
          </label>
          <label>Month:
            <select
              className="border border-white px-4 py-2 rounded-xl"
              value={month}
              onChange={(event) => { setMonth(parseInt(event.target.value)) }}
            >
              {months.map((month, i) => <option value={i} key={i}>{month}</option>)}
            </select>
          </label>
        </div>
      </div>
      <AreaChart
        className="aspect-[3/1]"
        responsive
        data={debitTransactions}
        margin={{}}
      >
        <Tooltip content={toolTip} />
        <XAxis dataKey={getXAxisLabel} fontSize={10} />
        <YAxis fontSize={10} />
        <Area dataKey="amount" isAnimationActive={true} />
      </AreaChart>
    </div>;
  }

}

export default Dashboard;

