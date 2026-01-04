package com.example.finance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.room.Room
import com.example.finance.data.FinanceDatabase
import com.example.finance.data.Transaction
import com.example.finance.data.TransactionType
import com.example.finance.ui.theme.FinanceTheme
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            this,
            FinanceDatabase::class.java, "finance_database"
            )
            .allowMainThreadQueries()
            .build()
        enableEdgeToEdge()
        setContent {
            FinanceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        HomePage(db)
                    }
                }
            }
        }
    }
}

@Composable
fun HomePage(db: FinanceDatabase) {
    val amount = rememberTextFieldState()
    val isAmountError = rememberSaveable { mutableStateOf(false) }
    val types = listOf(TransactionType.DEBIT, TransactionType.CREDIT)
    val (selectedType, onTypeSelected) = remember { mutableStateOf(types[0]) }
    val description = rememberTextFieldState()
    val isDescriptionError = rememberSaveable { mutableStateOf((false)) }
    val tags = rememberTextFieldState()
    val isTagsError = rememberSaveable { mutableStateOf(false) }
    val date = rememberDatePickerState(
        initialSelectedDateMillis = Date().time,
        initialDisplayMode = DisplayMode.Input
    )
    NumberInput(isAmountError, "Amount", "100", amount)
    SelectType(types, selectedType, onTypeSelected)
    TextInput(isDescriptionError, "Description", "Ate lunch in canteen", description)
    TextInput(isTagsError, "Tags", "Food", tags)
    SelectDate(date)
    Button(
        onClick = {
            isAmountError.value = amount.text.isEmpty()
            isDescriptionError.value = description.text.isEmpty()
            isTagsError.value = tags.text.isEmpty()

            if (!(isAmountError.value || isDescriptionError.value || isTagsError.value)) {
                db.transactionDao().insert(
                    Transaction(
                        amount = amount.text.toString().toDouble(),
                        type = selectedType,
                        description = description.text.toString(),
                        tags = tags.text.toString(),
                        date = date.selectedDateMillis ?: Date().time
                    )
                )
            }
        }
    ) {
        Text("Submit")
    }
}

@Composable
fun NumberInput(isError: MutableState<Boolean>, label: String, placeholder: String, state: TextFieldState) {
    Column {
        TextField(
            isError = isError.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(if (isError.value) "$label*" else label) },
            modifier = Modifier,
            placeholder = { Text(placeholder) },
            state = state,
            supportingText = {
                Text(if (isError.value) "Please enter a value" else "")
            },
        )
    }
}

@Composable
fun SelectType(types: List<TransactionType>, selectedType: TransactionType, onTypeSelected: (TransactionType) -> Unit) {
    Row(Modifier.selectableGroup()) {
        types.forEach { type ->
            Row(
                Modifier.selectable(
                        selected = (type == selectedType),
                        onClick = { onTypeSelected(type) },
                        role = Role.RadioButton,
                    )
            ) {
                RadioButton(
                    selected = type == selectedType,
                    onClick = null,
                )
                Text(type.toString())
            }
        }
    }
}

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
                Text(if (isError.value) "Please enter a value" else "")
            }
        )
    }
}

@Composable
fun SelectDate(date: DatePickerState) {
    Column {
        DatePicker(
            state = date,
        )
    }
}
