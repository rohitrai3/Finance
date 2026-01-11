package com.rohitrai.finance

import android.app.Activity
import android.content.Intent
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.opencsv.bean.StatefulBeanToCsvBuilder
import com.rohitrai.finance.data.FinanceDatabase
import com.rohitrai.finance.data.Transaction
import com.rohitrai.finance.ui.component.NavBar
import com.rohitrai.finance.ui.component.TopBar
import com.rohitrai.finance.ui.theme.FinanceTheme
import java.io.FileNotFoundException
import java.io.FileWriter
import java.io.IOException

class MainActivity : ComponentActivity() {
    var transactions = listOf<Transaction>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            this,
            FinanceDatabase::class.java, "finance_database"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration(true)
            .build()
        enableEdgeToEdge()
        setContent {
            transactions = db.transactionDao().getAll()
                .collectAsStateWithLifecycle(initialValue = listOf()).value
            FinanceApp(this, db)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2 && resultCode == RESULT_OK) {
            data?.data?.also { uri ->
                try {
                    contentResolver.openFileDescriptor(uri, "w")?.use {
                        val writer = FileWriter(it.fileDescriptor)
                        val beanToCsv = StatefulBeanToCsvBuilder<List<Transaction>>(writer).build()
                        beanToCsv.write(transactions)
                        writer.close()
                    }
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}

@Composable
fun FinanceApp(activity: Activity, db: FinanceDatabase) {
    FinanceTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            financeScreens.find { it.route == currentDestination?.route } ?: ViewTransactions
        Scaffold(
            bottomBar = {
                NavBar(
                    currentScreen = currentScreen,
                    onTabSelected = { screen -> navController.navigateSingleTopTo(screen.route) },
                    screens = financeScreens
                )
            },
            topBar = { TopBar(activity) }
        ) { innerPadding ->
            FinanceNavHost(
                db,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                navController = navController
            )
        }
    }
}
