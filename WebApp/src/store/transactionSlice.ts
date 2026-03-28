import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import type { GetTransactionsResponse } from "../types";

export const transactionApiSlice = createApi({
  baseQuery: fetchBaseQuery({ baseUrl: `${import.meta.env.VITE_BACKEND_URL}transaction/` }),
  reducerPath: "transactionApi",
  tagTypes: ["Transaction"],
  endpoints: build => ({
    getTransactions: build.query<GetTransactionsResponse, string>({
      query: (path = "get") => path,
      providesTags: (_result, _error, id) => [{ type: "Transaction", id }],
    }),
  }),
});

export const { useGetTransactionsQuery } = transactionApiSlice;

