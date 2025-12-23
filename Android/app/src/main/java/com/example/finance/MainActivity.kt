package com.example.finance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.finance.ui.theme.FinanceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinanceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        InputField("Amount", "123")
                        InputField("Type", "Debit/Credit")
                        InputField("Type", "Debit/Credit")
                        InputField("Description", "Eat lunch from canteen")
                        InputField("Tags", "Food")
                    }
                }
            }
        }
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
        }
    }
}