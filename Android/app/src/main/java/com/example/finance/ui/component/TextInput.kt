package com.example.finance.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.finance.R

@Composable
fun TextInput(isError: MutableState<Boolean>, label: String, placeholder: String, state: TextFieldState) {
    Column {
        TextField(
            isError = isError.value,
            label = { Text(if (isError.value) "$label*" else label) },
            modifier = Modifier,
            placeholder = { Text(placeholder) },
            state = state,
            supportingText = {
                Text(if (isError.value) stringResource(R.string.add_error_message) else "")
            }
        )
    }
}
