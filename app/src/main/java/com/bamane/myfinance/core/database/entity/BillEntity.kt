package com.bamane.myfinance.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "bills")
data class BillEntity (
    @PrimaryKey(autoGenerate = true) val id : Int,
    val title: String,
    val date: Long,
    val tax: Double = 0.0,
    val serviceCharge: Double = 0.0
)