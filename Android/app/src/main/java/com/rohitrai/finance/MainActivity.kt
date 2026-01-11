package com.rohitrai.finance

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.room.Room
import com.opencsv.bean.CsvToBeanBuilder
import com.opencsv.bean.StatefulBeanToCsvBuilder
import com.rohitrai.finance.data.FinanceDatabase
import com.rohitrai.finance.data.Transaction
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

class MainActivity : ComponentActivity() {
    var db: FinanceDatabase? = null
    var transactions = listOf<Transaction>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(
            this, FinanceDatabase::class.java, "finance_database"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration(true)
            .build()
        enableEdgeToEdge()
        setContent {
            transactions = db?.transactionDao()?.getAll()
                ?.collectAsStateWithLifecycle(listOf())?.value ?: listOf()
            FinanceApp(this, db)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(
        requestCode: Int, resultCode: Int, data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            data?.data?.also { uri ->
                try {
                    contentResolver.openFileDescriptor(uri, "r")?.use {
                        val transactions = CsvToBeanBuilder<Transaction>(
                            FileReader(it.fileDescriptor)
                        ).withType(Transaction::class.java).build().parse()
                        transactions.forEach { transaction ->
                            db?.transactionDao()?.insert(transaction)
                        }
                    }
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

        if (requestCode == 2 && resultCode == RESULT_OK) {
            data?.data?.also { uri ->
                try {
                    contentResolver.openFileDescriptor(uri, "w")?.use {
                        val writer = FileWriter(it.fileDescriptor)
                        val beanToCsv = StatefulBeanToCsvBuilder<Transaction>(writer).build()
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
