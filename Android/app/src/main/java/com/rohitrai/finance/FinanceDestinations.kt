package com.rohitrai.finance

sealed interface FinanceDestination {
    val route: String
}

data object ViewTransactions: FinanceDestination {
    override val route = "View transaction"
}

object AddTransaction: FinanceDestination {
    override val route = "Add transaction"
}

val financeScreens = listOf(ViewTransactions, AddTransaction)
