package com.bamane.myfinance.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "bills",
    foreignKeys = [
        ForeignKey(
            entity = PersonEntity::class,
            parentColumns = ["id"],
            childColumns = ["lenderId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BillEntity (
    @PrimaryKey(autoGenerate = true) val id : Int,
    val lenderId: Int,
    val title: String,
    val date: Long,
    val tax: Double = 0.0,
    val serviceCharge: Double = 0.0,
    val dueDate: Long? = null,
    val status: BillStatus = BillStatus.PENDING
)