package com.rohitrai.finance.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rohitrai.finance.R
import com.rohitrai.finance.data.TransactionType
import com.rohitrai.finance.ui.component.NumberInput
import com.rohitrai.finance.ui.component.SelectDate
import com.rohitrai.finance.ui.component.SelectType
import com.rohitrai.finance.ui.component.TextInput
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
    Column(
        modifier = Modifier.imePadding().fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
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
}
