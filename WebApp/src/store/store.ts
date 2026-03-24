import { combineSlices, configureStore } from "@reduxjs/toolkit";
import { transactionApiSlice } from "./transactionSlice";
import { setupListeners } from "@reduxjs/toolkit/query";

const rootReducer = combineSlices(transactionApiSlice);

export type RootState = ReturnType<typeof rootReducer>;

export const makeStore = (preloadedState?: Partial<RootState>) => {
  const store = configureStore({
    reducer: rootReducer,
    middleware: getDefaultMiddleware => {
      return getDefaultMiddleware().concat(transactionApiSlice.middleware);
    },
    preloadedState,
  });

  setupListeners(store.dispatch);

  return store;
}

export const store = makeStore();
export type AppStore = typeof store;
export type AppDispatch = AppStore["dispatch"];

