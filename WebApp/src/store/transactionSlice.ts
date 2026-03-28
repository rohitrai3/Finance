import type { Transaction } from "../types";
import { createSlice, type PayloadAction } from "@reduxjs/toolkit";
import { faker } from "@faker-js/faker";

export type TransactionSliceState = {
  transactions: Transaction[]
}

const initialState: TransactionSliceState = {
  transactions: getRandomTransactions(),
}

function getRandomTransactions(): Transaction[] {
  const transactions: Transaction[] = [];

  for (let i = 0; i < 1462; i++) {
    transactions.push({
      id: faker.string.uuid(),
      amount: parseInt(faker.finance.amount()),
      type: faker.helpers.arrayElement(["CREDIT", "DEBIT"]),
      description: faker.finance.transactionDescription(),
      tags: "",
      date: faker.date.between({ from: "2024-01-01T00:00:00.000Z", to: Date.now() }).getTime(),
      createTime: 0,
      updateTime: 0
    });
  }

  return transactions;
}

export const transactionSlice = createSlice({
  name: "transaction",
  initialState,
  reducers: create => ({
    addTransaction: create.reducer((state, action: PayloadAction<Transaction>) => {
      state.transactions.push(action.payload)
    })
  }),
  selectors: {
    selectTransactions: transaction => transaction.transactions
  },
})

export const { addTransaction } = transactionSlice.actions;
export const { selectTransactions } = transactionSlice.selectors;

