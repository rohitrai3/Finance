import { faker } from "@faker-js/faker";
import { useAppDispatch } from "../store/hooks";
import { Type, type Transaction } from "../types";
import { addTransaction } from "../store/transactionSlice";

function AddTransactionForm() {
  const dispatch = useAppDispatch();

  async function add(formData: FormData) {
    const transaction: Transaction = {
      id: faker.string.uuid(),
      amount: parseInt(formData.get("amount")?.toString()!),
      type: formData.get("type")?.toString()!,
      description: formData.get("description")?.toString()!,
      tags: formData.get("tags")?.toString()!,
      date: Date.parse(formData.get("date")?.toString()!),
      createTime: 0,
      updateTime: 0,
    };

    dispatch(addTransaction(transaction));
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

