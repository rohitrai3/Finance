function Homepage() {
  return <div className="flex h-screen items-center justify-center w-screen">
    <form className="flex flex-col gap-6 w-lg">
      <input className='border border-white px-4 py-2 rounded-xl' placeholder='Amount' type='text' />
      <div className="flex">
        <div className="flex flex-1 gap-2">
          <input name="type" type="radio" value="debit" />
          <label>Debit</label>
        </div>
        <div className="flex flex-1 gap-2">
          <input name="type" type="radio" value="credit" />
          <label>Credit</label>
        </div>
      </div>
      <input className='border border-white px-4 py-2 rounded-xl' placeholder='Description' type='text' />
      <input className='border border-white px-4 py-2 rounded-xl' placeholder='Tags' type='text' />
      <input className="border border-white px-4 py-2 rounded-xl" type="date" />
      <div className="flex justify-end">
        <input className="bg-gray px-4 py-2 rounded-xl w-fit" type="submit" value="Add" />
      </div>
    </form>
  </div>
}

export default Homepage

