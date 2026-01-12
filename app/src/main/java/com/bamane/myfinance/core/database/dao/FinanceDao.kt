package com.bamane.myfinance.core.database.dao

import android.app.Person
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bamane.myfinance.core.database.entity.BillEntity
import com.bamane.myfinance.core.database.entity.BillItemEntity
import com.bamane.myfinance.core.database.entity.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FinanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: PersonEntity)

    @Query("SELECT * FROM persons ORDER BY name ASC")
    fun getAllFriends() : Flow<List<PersonEntity>>

    @Query("SELECT * FROM persons WHERE isMe = 1 LIMIT 1")
    suspend fun getMyProfile() : PersonEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBill(bill: BillEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBillItem(billItem: BillItemEntity): Long

    @Transaction
    suspend fun insertFullBill(bill: BillEntity, items: List<BillItemEntity>) {
        val billId = insertBill(bill)
        items.forEach { item ->
            insertBillItem(item.copy(billId = billId.toInt()))
        }
    }
}