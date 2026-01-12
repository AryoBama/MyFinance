package com.bamane.myfinance.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "bill_items",
    foreignKeys = [
        ForeignKey(
            entity = BillEntity::class,
            parentColumns = ["id"],
            childColumns = ["billId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BillItemEntity(
    @PrimaryKey(autoGenerate = true) val id : Int,
    val billId: Int,
    val name: String,
    val price: Double,
    val quantity: Int = 1
)