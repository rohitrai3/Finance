package com.rohitrai.finance.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rohitrai.finance.R
import com.rohitrai.finance.ui.theme.Gray
import com.rohitrai.finance.ui.theme.White
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SelectDate(date: DatePickerState) {
    var isShowDataPicker by rememberSaveable { mutableStateOf(false) };
    BasicTextField(
        decorationBox = { innerTextField ->
            Row(horizontalArrangement = Arrangement.Absolute.Right) {
                    Icon(
                        contentDescription = stringResource(R.string.edit_calendar_icon),
                        modifier = Modifier.clickable(
                            onClick = { isShowDataPicker = !isShowDataPicker}
                        ),
                        painter = painterResource(R.drawable.edit_calendar)
                    )
            }
            innerTextField()
        },
        modifier = Modifier
            .border(width = 1.dp, color = Gray, shape = RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(16.dp),
        onValueChange = {},
        readOnly = true,
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = White
        ),
        value = date.selectedDateMillis?.let { convertMillisToDate(it) } ?: ""
    )

    if (isShowDataPicker) {
        DatePickerDialog(
            onDismissRequest = {},
            confirmButton = {
                TextButton(
                    onClick = {
                        isShowDataPicker = !isShowDataPicker
                    }
                ) { Text(stringResource(R.string.ok_label)) }
            }
        ) {
            DatePicker(
                state = date
            )
        }
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())

    return formatter.format(Date(millis))
}
