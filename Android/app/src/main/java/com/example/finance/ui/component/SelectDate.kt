package com.example.finance.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.runtime.Composable

@Composable
fun SelectDate(date: DatePickerState) {
    Column {
        DatePicker(
            focusRequester = null,
            headline = null,
            showModeToggle = false,
            state = date,
            title = null
        )
    }
}
