import { Type, type AddTransactionInput } from "../types";

function AddTransactionForm() {
  async function add(formData: FormData) {
    const addTransactionInput: AddTransactionInput = {
      amount: parseInt(formData.get("amount")?.toString()!),
      type: formData.get("type")?.toString()!,
      description: formData.get("description")?.toString()!,
      tags: formData.get("tags")?.toString()!,
      date: Date.parse(formData.get("date")?.toString()!)
    };

    await fetch("http://localhost:8080/transaction/add", {
      body: JSON.stringify(addTransactionInput),
      headers: {
        "Content-Type": "application/json"
      },
      method: "POST"
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        console.log("data: ", data);
      })
      .catch((err) => {
        console.log("Error: ", err);
      });
  }

  return <div className="flex h-screen items-center justify-center w-3xs">
    <form action={add} className="flex flex-col gap-6 w-lg">
      <input className="border border-white px-4 py-2 rounded-xl" name="amount" placeholder="Amount" type="text" />
      <div className="flex">
        <label className="flex flex-1 gap-2 cursor-pointer">
          <input defaultChecked={true} name="type" type="radio" value={Type.DEBIT} />
          Debit
        </label>
        <label className="flex flex-1 gap-2 cursor-pointer">
          <input name="type" type="radio" value={Type.CREDIT} />
          Credit
        </label>
      </div>
      <textarea className="border border-white px-4 py-2 rounded-xl" name="description" placeholder="Description" />
      <input className="border border-white px-4 py-2 rounded-xl" name="tags" placeholder="Tags" type="text" />
      <input className="border border-white px-4 py-2 rounded-xl" name="date" type="date" />
      <div className="flex justify-end">
        <input className="bg-gray hover:bg-purple px-4 py-2 rounded-xl w-fit cursor-pointer" type="submit" value="Add" />
      </div>
    </form>
  </div>;
}

export default AddTransactionForm;

