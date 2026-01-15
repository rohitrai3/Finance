package com.rohitrai.finance.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rohitrai.finance.data.Transaction
import com.rohitrai.finance.ui.component.ViewTransaction
import java.util.Collections

@Composable
fun ViewTransactionsScreen(transactions: List<Transaction> = Collections.emptyList()) {
    LazyColumn(
        modifier = Modifier.padding(0.dp, 4.dp),
        state = rememberLazyListState(initialFirstVisibleItemIndex = Int.MAX_VALUE),
        verticalArrangement = Arrangement.Bottom
    ) {
        itemsIndexed(transactions) { _, transaction ->
            ViewTransaction(transaction)
        }
    }
}
