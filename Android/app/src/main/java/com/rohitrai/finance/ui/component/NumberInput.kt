package com.rohitrai.finance.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rohitrai.finance.R
import com.rohitrai.finance.ui.theme.Gray
import com.rohitrai.finance.ui.theme.LightRed
import com.rohitrai.finance.ui.theme.Red
import com.rohitrai.finance.ui.theme.White

@Composable
fun NumberInput(
    isError: MutableState<Boolean>,
    label: String,
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
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number
        ),
        lineLimits = TextFieldLineLimits.SingleLine,
        modifier = Modifier
            .border(
                color = if (isError.value) Red else Gray,
                shape = RoundedCornerShape(16.dp),
                width = 1.dp
            )
            .fillMaxWidth()
            .padding(16.dp),
        state = state,
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = White
        )
    )
    if (isError.value) BasicText(
        style = TextStyle(color = Red),
        text = stringResource(R.string.add_error_message)
    )
}
