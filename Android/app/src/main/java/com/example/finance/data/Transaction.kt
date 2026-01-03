package com.example.finance.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val amount: Double,
    val type: TransactionType,
    val description: String,
    val tags: String,
    val date: Date,
    @ColumnInfo(name = "create_time")
    val createTime: Date,
    @ColumnInfo(name = "update_time")
    val updateTime: Date,
)
