package com.bamane.myfinance.core.database

import androidx.room.TypeConverter
import com.bamane.myfinance.core.database.entity.BillStatus

class Converters {
    @TypeConverter
    fun fromStatus(status: BillStatus): String {
        return status.name
    }

    @TypeConverter
    fun toStatus(value: String): BillStatus {
        return try {
            BillStatus.valueOf(value)
        } catch (e: Exception) {
            BillStatus.PENDING
        }
    }
}