import AddTransactionForm from "./components/AddTransactionForm";
import ViewTransactions from "./components/ViewTransactions";

function Homepage() {
  return <div className="flex h-screen items-center justify-center p-4 w-screen">
    <ViewTransactions />
  </div>;
}

export default Homepage;

