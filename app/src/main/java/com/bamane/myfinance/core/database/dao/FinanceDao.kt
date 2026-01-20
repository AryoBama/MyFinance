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
import com.bamane.myfinance.core.model.BillPreviewModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FinanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: PersonEntity)

    @Query("SELECT * FROM persons ORDER BY name ASC")
    fun getAllFriends() : Flow<List<PersonEntity>>

    @Query("SELECT * FROM persons WHERE isMe = 1 LIMIT 1")
    suspend fun getMyProfile() : PersonEntity?

    @Query("""
        SELECT COALESCE(SUM(bi.price), 0.0)
        FROM bill_items bi
        JOIN item_assignments ia ON bi.id = ia.itemId
        JOIN bills b ON bi.billId = b.id
        WHERE 
            b.lenderId = (SELECT id FROM persons WHERE isMe = 1 LIMIT 1) 
            AND 
            ia.friendId != (SELECT id FROM persons WHERE isMe = 1 LIMIT 1)
    """)
    fun getTotalReceivables(): Flow<Double>

    @Query("""
        SELECT COALESCE(SUM(bi.price), 0.0)
        FROM bill_items bi
        JOIN item_assignments ia ON bi.id = ia.itemId
        JOIN bills b ON bi.billId = b.id
        WHERE 
            b.lenderId != (SELECT id FROM persons WHERE isMe = 1 LIMIT 1) 
            AND 
            ia.friendId = (SELECT id FROM persons WHERE isMe = 1 LIMIT 1)
    """)
    fun getTotalDebt(): Flow<Double>

    @Query("""
        SELECT 
            b.id AS billId,
            b.title AS title,
            b.date AS date,
            
            COALESCE(SUM(bi.price), 0) AS totalAmount,
            
            GROUP_CONCAT(DISTINCT p.name) AS participantNames,
            
            COUNT(DISTINCT ia.friendId) AS participantCount
            
        FROM bills b
        
        LEFT JOIN bill_items bi ON b.id = bi.billId
        
        LEFT JOIN item_assignments ia ON bi.id = ia.itemId
        
        LEFT JOIN persons p ON ia.friendId = p.id
        
        GROUP BY b.id
        
        ORDER BY b.date DESC
        
        LIMIT :limit
    """)
    fun getBillPreviews(limit: Int): Flow<List<BillPreviewModel>>

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