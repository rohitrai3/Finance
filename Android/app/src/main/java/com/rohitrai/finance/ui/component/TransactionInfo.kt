package com.rohitrai.finance.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.rohitrai.finance.R
import com.rohitrai.finance.data.FinanceDatabase
import com.rohitrai.finance.data.Transaction
import com.rohitrai.finance.data.TransactionType
import com.rohitrai.finance.ui.theme.Black
import com.rohitrai.finance.ui.theme.Gray
import com.rohitrai.finance.utils.convertMillisToDate
import java.util.Date

@Composable
fun TransactionInfo(
    db: FinanceDatabase?,
    isShowSnackBar: MutableState<Boolean>,
    isViewTransactionInfo: MutableState<Boolean>,
    transaction: Transaction
) {
    Dialog(
        onDismissRequest = { isViewTransactionInfo.value = false }
    ) {
        val isAmountError = remember { mutableStateOf(false) }
        val amount = rememberTextFieldState(transaction.amount.toString())
        val types = listOf(TransactionType.DEBIT, TransactionType.CREDIT)
        val (selectedType, onTypeSelected) = remember { mutableStateOf(transaction.type) }
        val isDescriptionError = remember { mutableStateOf(false) }
        val description = rememberTextFieldState(transaction.description)
        val isTagsError = remember { mutableStateOf(false) }
        val tags = rememberTextFieldState(transaction.tags)
        val date = rememberDatePickerState(
            initialSelectedDateMillis = transaction.date,
            initialDisplayMode = DisplayMode.Input
        )
        val createDate = convertMillisToDate(transaction.createTime)
        val updateDate = convertMillisToDate(transaction.updateTime)
        val focusManager = LocalFocusManager.current

        fun updateTransaction() {
            focusManager.clearFocus()
            isAmountError.value = amount.text.isEmpty()
            isDescriptionError.value = description.text.isEmpty()
            isTagsError.value = tags.text.isEmpty()

            if (!(isAmountError.value || isDescriptionError.value || isTagsError.value)) {
                db?.transactionDao()?.update(
                    Transaction(
                        id = transaction.id,
                        amount = amount.text.toString().toDouble(),
                        type = selectedType,
                        description = description.text.toString(),
                        tags = tags.text.toString(),
                        date = date.selectedDateMillis ?: Date().time,
                        createTime = transaction.createTime,
                        updateTime = Date().time
                    )
                )

                isViewTransactionInfo.value = false
                isShowSnackBar.value = true
            }
        }

        Column(
            modifier = Modifier
                .background(Black)
                .border(2.dp, Gray, RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Text(
                color = Gray,
                text = "Id: ${transaction.id}"
            )
            Text(
                color = Gray,
                modifier = Modifier.padding(0.dp, 0.dp, 8.dp, 0.dp),
                text = "${stringResource(R.string.amount_label)}:"
            )
            NumberInput(
                isAmountError,
                stringResource(R.string.amount_label),
                amount
            )
            Text(
                color = Gray,
                modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
                text = "${stringResource(R.string.type_label)}:"
            )
            SelectType(types, selectedType, onTypeSelected)
            Text(
                color = Gray,
                modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
                text = "${stringResource(R.string.description_label)}:"
            )
            TextInput(
                isDescriptionError,
                stringResource(R.string.description_label),
                description)
            Text(
                color = Gray,
                modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
                text = "${stringResource(R.string.tags_label)}:"
            )
            TextInput(
                isTagsError,
                stringResource(R.string.tags_label),
                tags
            )
            Text(
                color = Gray,
                modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
                text = "${stringResource(R.string.date_label)}:"
            )
            SelectDate(date)
            Text(
                color = Gray,
                modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
                text = "Creation time: $createDate"
            )
            Text(
                color = Gray,
                modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
                text = "Update time: $updateDate"
            )
            Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { updateTransaction() }
                ) {
                    Text(stringResource(R.string.update_label))
                }
            }
        }
    }
}
