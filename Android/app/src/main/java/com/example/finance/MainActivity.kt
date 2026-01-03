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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val types = listOf(TransactionType.DEBIT, TransactionType.CREDIT)
    val (selectedType, onTypeSelected) = remember { mutableStateOf(types[0]) }
    val description = rememberTextFieldState()
    val tags = rememberTextFieldState()
    val date = rememberDatePickerState(
        initialSelectedDateMillis = Date().time,
        initialDisplayMode = DisplayMode.Input
    )
    NumberInput("Amount", "100", amount)
    SelectType(types, selectedType, onTypeSelected)
    TextInput("Description", "Ate lunch in canteen", description)
    TextInput("Tags", "Food", tags)
    SelectDate(date)
    Button(
        onClick = {
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
    ) {
        Text("Submit")
    }
}

@Composable
fun NumberInput(label: String, placeholder: String, state: TextFieldState, modifier: Modifier = Modifier) {
    Column {
        TextField(
            state = state,
            label = { Text(label) },
            placeholder = { Text(placeholder) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier,
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
fun TextInput(label: String, placeholder: String, state: TextFieldState, modifier: Modifier = Modifier) {
    Column {
        TextField(
            state = state,
            label = { Text(label) },
            placeholder = { Text(placeholder) },
            modifier = modifier,
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
