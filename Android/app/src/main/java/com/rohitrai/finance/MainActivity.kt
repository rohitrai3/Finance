package com.rohitrai.finance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.rohitrai.finance.data.FinanceDatabase
import com.rohitrai.finance.ui.component.NavBar
import com.rohitrai.finance.ui.theme.FinanceTheme
import kotlin.jvm.java

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
            FinanceApp(db)
        }
    }
}

@Composable
fun FinanceApp(db: FinanceDatabase) {
    FinanceTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            financeScreens.find { it.route == currentDestination?.route } ?: ViewTransactions
        Scaffold(
            bottomBar = { NavBar(
                currentScreen = currentScreen,
                onTabSelected = { screen -> navController.navigateSingleTopTo(screen.route) },
                screens = financeScreens
            ) },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            FinanceNavHost(
                db,
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}
