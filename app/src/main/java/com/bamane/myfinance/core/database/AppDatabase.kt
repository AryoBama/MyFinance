package com.bamane.myfinance.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bamane.myfinance.core.database.entity.BillEntity
import com.bamane.myfinance.core.database.entity.BillItemEntity
import com.bamane.myfinance.core.database.entity.PersonEntity
import com.bamane.myfinance.core.database.entity.ItemAssignmentEntity

@Database(
    entities = [
        PersonEntity::class,
        BillEntity::class,
        BillItemEntity::class,
        ItemAssignmentEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "finance_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}