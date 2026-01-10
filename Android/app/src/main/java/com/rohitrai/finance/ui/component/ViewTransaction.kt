package com.rohitrai.finance.ui.component

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
import com.rohitrai.finance.R
import com.rohitrai.finance.data.Transaction
import java.util.Date

@Composable
fun ViewTransaction(transaction: Transaction) {
    val dateFormat = DateFormat.getDateInstance()
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
                    text = dateFormat.format(Date(transaction.date))
                )
            }
        }
        Text(transaction.description)
    }
}