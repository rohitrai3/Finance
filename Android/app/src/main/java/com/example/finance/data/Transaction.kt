package com.example.finance.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Double,
    val type: TransactionType,
    val description: String,
    val tags: String,
    val date: Long,
    @ColumnInfo(name = "create_time")
    val createTime: Long = Date().time,
    @ColumnInfo(name = "update_time")
    val updateTime: Long = Date().time,
)
