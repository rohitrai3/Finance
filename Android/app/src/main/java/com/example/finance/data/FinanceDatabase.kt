package com.example.finance.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
abstract class FinanceDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}