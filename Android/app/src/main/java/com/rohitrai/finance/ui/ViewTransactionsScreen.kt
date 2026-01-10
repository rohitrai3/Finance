package com.rohitrai.finance.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import com.rohitrai.finance.data.Transaction
import com.rohitrai.finance.ui.component.ViewTransaction
import java.util.Collections

@Composable
fun ViewTransactionsScreen(transactions: List<Transaction> = Collections.emptyList()) {
    LazyColumn(
        state = rememberLazyListState(initialFirstVisibleItemIndex = Int.MAX_VALUE),
        verticalArrangement = Arrangement.Bottom
    ) {
        itemsIndexed(transactions) { _, transaction ->
            ViewTransaction(transaction)
        }
    }
}
