package com.example.finance.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finance.R
import com.example.finance.ui.theme.Gray
import com.example.finance.ui.theme.LightRed
import com.example.finance.ui.theme.Red
import com.example.finance.ui.theme.White

@Composable
fun TextInput(
    isError: MutableState<Boolean>,
    label: String,
    placeholder: String,
    state: TextFieldState
) {
    BasicTextField(
        decorator = { innerTextField ->
            if(state.text.isEmpty()) BasicText(text = label, style = TextStyle(
                color = if (isError.value) LightRed else Gray,
                fontSize = 16.sp
            ))
            innerTextField()
        },
        modifier = Modifier
            .border(width = 1.dp, color = if (isError.value) Red else Gray, shape = RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(16.dp),
        state = state,
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = White
        )
    )
    if (isError.value) BasicText(style = TextStyle(color = Red), text = stringResource(R.string.add_error_message))
}
