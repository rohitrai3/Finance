package com.example.finance

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    val transaction: Transaction = Transaction(
        id = 1,
        amount = 10.0,
        type = TransactionType.DEBIT,
        description = "Test payment",
        tags = "Test",
        date = Date(),
        createTime = Date(),
        updateTime = Date()
    )
    InputField("Amount", "123")
    InputField("Type", "Debit/Credit")
    InputField("Type", "Debit/Credit")
    InputField("Description", "Eat lunch from canteen")
    InputField("Tags", "Food")
    Button(
        onClick = {
            db.transactionDao().insert(transaction)
        }
    ) {
        Text("Submit")
    }
}

@Composable
fun InputField(label: String, value: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = label,
            modifier = modifier
        )
        TextField(
            value = value,
            onValueChange = {},
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InputFieldPreview() {
    FinanceTheme {
        Column {
            InputField("Amount", "123")
            InputField("Type", "Debit/Credit")
            InputField("Type", "Debit/Credit")
            InputField("Description", "Eat lunch from canteen")
            InputField("Tags", "Food")
            Button(
                onClick = {}
            ) {
                Text("Submit")
            }
        }
    }
}