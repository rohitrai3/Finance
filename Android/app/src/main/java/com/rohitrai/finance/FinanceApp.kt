package com.rohitrai.finance

import android.app.Activity
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rohitrai.finance.data.FinanceDatabase
import com.rohitrai.finance.ui.component.NavBar
import com.rohitrai.finance.ui.component.NotificationBar
import com.rohitrai.finance.ui.component.TopBar
import com.rohitrai.finance.ui.theme.FinanceTheme

@Composable
fun FinanceApp(activity: Activity, db: FinanceDatabase?) {
    FinanceTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            financeScreens.find { it.route == currentDestination?.route } ?: ViewTransactions
        val snackBarHostState = remember { SnackbarHostState() }

        Scaffold(
            bottomBar = { NavBar(
                    currentScreen = currentScreen,
                    onTabSelected = { screen -> navController.navigateSingleTopTo(screen.route) },
                    screens = financeScreens
                ) },
            snackbarHost = { NotificationBar(snackBarHostState) },
            topBar = { TopBar(activity) }
        ) { innerPadding ->
            FinanceNavHost(
                db,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding),
                navController = navController,
                snackBarHostState
            )
        }
    }
}
