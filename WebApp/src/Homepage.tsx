import { useState } from "react";
import AddTransactionForm from "./components/AddTransactionForm";
import ViewTransactions from "./components/ViewTransactions";
import { AddIcon } from "./icons";
import Import from "./components/Import";
import Export from "./components/Export";

function Homepage() {
  const [isViewForm, setIsViewForm] = useState<boolean>(false);

  function onClick() {
    setIsViewForm(!isViewForm);
  }

  return <div className="flex flex-col md:flex-row gap-8 h-screen items-center p-4 pr-16 w-screen">
    <div className="fixed right-4 top-4 flex gap-4 flex flex-col">
      <Import />
      <Export />
    </div>
    <ViewTransactions />
    {isViewForm && <AddTransactionForm />}
    <button className="bg-gray bottom-4 fixed p-2 right-4 rounded-full" onClick={onClick}>
      {AddIcon()}
    </button>
  </div>;
}

export default Homepage;

