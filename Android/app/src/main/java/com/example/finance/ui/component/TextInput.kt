package com.example.finance.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.finance.R
import com.example.finance.ui.theme.TextInputDarkColorScheme

@Composable
fun TextInput(
    isError: MutableState<Boolean>,
    label: String,
    placeholder: String,
    state: TextFieldState
) {
    Column( modifier = Modifier.padding(16.dp) ) {
        OutlinedTextField(
            colors = TextInputDarkColorScheme,
            isError = isError.value,
            label = { Text(if (isError.value) "$label*" else label) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder) },
            shape = RoundedCornerShape(16.dp),
            state = state,
            supportingText = {
                Text(
                    if (isError.value) stringResource(R.string.add_error_message) else ""
                )
            }
        )
    }
}
