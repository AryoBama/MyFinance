package com.bamane.myfinance.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "item_assignments",
    primaryKeys = ["itemId", "friendId"],
    foreignKeys = [
        ForeignKey(
            entity = BillItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["itemId"],
            onDelete = ForeignKey.CASCADE),
        ForeignKey(
            entity = PersonEntity::class,
            parentColumns = ["id"],
            childColumns = ["friendId"],
            onDelete = ForeignKey.CASCADE)
    ]
)
data class ItemAssignmentEntity (
    val itemId: Int,
    val friendId: Int
)