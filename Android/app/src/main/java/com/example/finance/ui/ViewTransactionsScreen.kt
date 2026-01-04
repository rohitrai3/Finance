package com.example.finance.ui

import android.icu.text.DateFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finance.R
import com.example.finance.data.Transaction
import java.util.Collections
import java.util.Date

@Composable
fun ViewTransactionsScreen(transactions: List<Transaction> = Collections.emptyList()) {
    val df = DateFormat.getDateInstance()
    Column {
        transactions.forEach { transaction ->
            Column(
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        fontSize = 16.sp,
                        text = stringResource(R.string.rupee_symbol)
                    )
                    Text(
                        fontSize = 40.sp,
                        text = transaction.amount.toString()
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            fontSize = 16.sp,
                            text = transaction.type.toString(),
                            textAlign = TextAlign.Right
                        )
                        Text(
                            fontSize = 16.sp,
                            text = df.format(Date(transaction.date))
                        )
                    }
                }
                Text(transaction.description)
            }
        }
    }
}
