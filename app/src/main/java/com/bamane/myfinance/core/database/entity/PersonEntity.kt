package com.bamane.myfinance.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phone: String? = null,
    val profileImage: String?,
    val isMe: Boolean = false
)