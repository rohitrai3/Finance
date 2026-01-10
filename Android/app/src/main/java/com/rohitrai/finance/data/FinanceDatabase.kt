package com.rohitrai.finance.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rohitrai.finance.data.TransactionDao

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
abstract class FinanceDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}