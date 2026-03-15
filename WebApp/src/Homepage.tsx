import { useState } from "react";
import AddTransactionForm from "./components/AddTransactionForm";
import ViewTransactions from "./components/ViewTransactions";
import { AddIcon } from "./icons";

function Homepage() {
  const [isViewForm, setIsViewForm] = useState<boolean>(false);

  function onClick() {
    setIsViewForm(!isViewForm);
  }

  return <div className="flex flex-col md:flex-row gap-8 h-screen items-center p-4 w-screen">
    <div className="overflow-auto">
      <ViewTransactions />
    </div>
    {isViewForm && <AddTransactionForm />}
    <button className="bg-gray bottom-4 fixed p-2 right-4 rounded-full" onClick={onClick}>
      {AddIcon("fill-white")}
    </button>
  </div>;
}

export default Homepage;

