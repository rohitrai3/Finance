package com.rohitrai.finance

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rohitrai.finance.data.FinanceDatabase
import com.rohitrai.finance.data.Transaction
import com.rohitrai.finance.data.TransactionType
import com.rohitrai.finance.ui.AddTransactionScreen
import com.rohitrai.finance.ui.ViewTransactionsScreen
import java.util.Date

@Composable
fun FinanceNavHost(
    db: FinanceDatabase?,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val transactions = db?.transactionDao()?.getAll()
        ?.collectAsStateWithLifecycle(initialValue = listOf())?.value ?: listOf()
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

    fun onAddButtonClick() {
        isAmountError.value = amount.text.isEmpty()
        isDescriptionError.value = description.text.isEmpty()
        isTagsError.value = tags.text.isEmpty()

        if (!(isAmountError.value || isDescriptionError.value || isTagsError.value)) {
            db?.transactionDao()?.insert(
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

    NavHost(
        navController = navController,
        startDestination = ViewTransactions.route,
        modifier = modifier.padding(16.dp, 0.dp)
    ) {
        composable(route = ViewTransactions.route) {
            ViewTransactionsScreen(transactions)
        }
        composable(route = AddTransaction.route) {
            AddTransactionScreen(
                amount,
                isAmountError,
                types,
                selectedType,
                onTypeSelected,
                description,
                isDescriptionError,
                tags,
                isTagsError,
                date
            ) { onAddButtonClick() }
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
