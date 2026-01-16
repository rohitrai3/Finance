package com.rohitrai.finance.ui.component

import android.annotation.SuppressLint
import android.icu.text.DateFormat
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rohitrai.finance.R
import com.rohitrai.finance.data.FinanceDatabase
import com.rohitrai.finance.data.Transaction
import com.rohitrai.finance.ui.theme.Gray
import kotlinx.coroutines.launch
import java.util.Date

@Composable
@SuppressLint("CoroutineCreationDuringComposition")
fun ViewTransaction(
    db: FinanceDatabase?,
    snackbarHostState: SnackbarHostState,
    transaction: Transaction
) {
    val dateFormat = DateFormat.getDateInstance()
    val isViewTransactionInfo = remember { mutableStateOf(false) }
    val isShowSnackBar = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val notificationMessage = stringResource(R.string.transaction_update_success_notification)

    if (isViewTransactionInfo.value) {
        TransactionInfo(db, isShowSnackBar, isViewTransactionInfo, transaction)
    }

    if (isShowSnackBar.value) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = notificationMessage,
                withDismissAction = true
            )
        }
        isShowSnackBar.value = false
    }

    Column(
        modifier = Modifier
            .padding(4.dp, 4.dp)
            .border(2.dp, Gray, RoundedCornerShape(16.dp))
            .clickable(onClick = { isViewTransactionInfo.value = true })
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp, 16.dp, 0.dp)
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
                horizontalAlignment = Alignment.End,
                modifier = Modifier.fillMaxWidth()
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
        Text(
            modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp),
            text = transaction.description
        )
    }
}