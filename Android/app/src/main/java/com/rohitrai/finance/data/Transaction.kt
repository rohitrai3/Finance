package com.rohitrai.finance.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.opencsv.bean.CsvBindByName
import java.util.Date

@Entity(tableName = "transactions")
data class Transaction(
    @CsvBindByName @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @CsvBindByName val amount: Double,
    @CsvBindByName val type: TransactionType,
    @CsvBindByName val description: String,
    @CsvBindByName val tags: String,
    @CsvBindByName val date: Long,
    @ColumnInfo @CsvBindByName val createTime: Long = Date().time,
    @ColumnInfo @CsvBindByName val updateTime: Long = Date().time,
) {
    constructor() : this(0, 0.0, TransactionType.DEBIT, "",
        "", 0, 0, 0) {}
}
