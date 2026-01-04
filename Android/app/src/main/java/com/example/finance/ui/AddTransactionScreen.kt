package com.example.finance.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import com.example.finance.R
import com.example.finance.data.TransactionType
import com.example.finance.ui.component.NumberInput
import com.example.finance.ui.component.SelectDate
import com.example.finance.ui.component.SelectType
import com.example.finance.ui.component.TextInput
import java.util.Collections

@Composable
fun AddTransactionScreen(
    amount: TextFieldState = TextFieldState(),
    isAmountError: MutableState<Boolean> = mutableStateOf(false),
    types: List<TransactionType> = Collections.emptyList(),
    selectedType: TransactionType = TransactionType.DEBIT,
    onTypeSelected: (TransactionType) -> Unit = {},
    description: TextFieldState = TextFieldState(),
    isDescriptionError: MutableState<Boolean> = mutableStateOf(false),
    tags: TextFieldState = TextFieldState(),
    isTagsError: MutableState<Boolean> = mutableStateOf(false),
    date: DatePickerState = rememberDatePickerState(),
    onAddButtonClick: () -> Unit = {}
) {
    Column {
        NumberInput(
            isAmountError,
            stringResource(R.string.amount_label),
            stringResource(R.string.amount_placeholder),
            amount
        )
        SelectType(types, selectedType, onTypeSelected)
        TextInput(
            isDescriptionError,
            stringResource(R.string.description_label),
            stringResource(R.string.description_placeholder),
            description
        )
        TextInput(
            isTagsError,
            stringResource(R.string.tags_label),
            stringResource(R.string.tags_placeholder),
            tags
        )
        SelectDate(date)
        Button(
            onClick = { onAddButtonClick() }
        ) {
            Text(stringResource(R.string.add_label))
        }
    }
}
