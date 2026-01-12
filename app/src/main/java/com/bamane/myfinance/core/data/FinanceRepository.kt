package com.bamane.myfinance.core.data

import com.bamane.myfinance.core.database.dao.FinanceDao
import com.bamane.myfinance.core.database.entity.BillEntity
import com.bamane.myfinance.core.database.entity.BillItemEntity
import com.bamane.myfinance.core.database.entity.PersonEntity
import kotlinx.coroutines.flow.Flow

class FinanceRepository(private val dao: FinanceDao) {
    val allFriends: Flow<List<PersonEntity>> = dao.getAllFriends()

    suspend fun addFriend(person: PersonEntity){
        dao.insertPerson(person)
    }

    suspend fun getMyProfile() {
        dao.getMyProfile()
    }

    suspend fun saveBill(bill: BillEntity, items: List<BillItemEntity>) {
        dao.insertFullBill(bill, items)
    }
}