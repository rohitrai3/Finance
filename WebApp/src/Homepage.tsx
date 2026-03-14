import { Type, type AddTransactionInput } from "./types";

function Homepage() {
  function add(formData: FormData) {
    const addTransactionInput: AddTransactionInput = {
      amount: parseInt(formData.get("amount")?.toString()!),
      type: formData.get("type")?.toString()!,
      description: formData.get("description")?.toString()!,
      tags: formData.get("tags")?.toString()!,
      date: Date.parse(formData.get("date")?.toString()!)
    };

    console.log('addTransactionInput: ', addTransactionInput);
  }

  return <div className='flex h-screen items-center justify-center w-screen'>
    <form action={add} className='flex flex-col gap-6 w-lg'>
      <input className='border border-white px-4 py-2 rounded-xl' name='amount' placeholder='Amount' type='text' />
      <div className='flex'>
        <label className='flex flex-1 gap-2'>
          <input defaultChecked={true} name='type' type='radio' value={Type.DEBIT} />
          Debit
        </label>
        <label className='flex flex-1 gap-2'>
          <input name='type' type='radio' value={Type.CREDIT} />
          Credit
        </label>
      </div>
      <input className='border border-white px-4 py-2 rounded-xl' name='description' placeholder='Description' type='text' />
      <input className='border border-white px-4 py-2 rounded-xl' name='tags' placeholder='Tags' type='text' />
      <input className='border border-white px-4 py-2 rounded-xl' name='date' type='date' />
      <div className='flex justify-end'>
        <input className='bg-gray px-4 py-2 rounded-xl w-fit' type='submit' value='Add' />
      </div>
    </form>
  </div>;
}

export default Homepage;

