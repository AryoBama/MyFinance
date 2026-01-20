package com.bamane.myfinance.core.data

import com.bamane.myfinance.core.database.dao.FinanceDao
import com.bamane.myfinance.core.database.entity.BillEntity
import com.bamane.myfinance.core.database.entity.BillItemEntity
import com.bamane.myfinance.core.database.entity.PersonEntity
import com.bamane.myfinance.core.model.BillPreviewModel
import kotlinx.coroutines.flow.Flow

class FinanceRepository(private val dao: FinanceDao) {
    val allFriends: Flow<List<PersonEntity>> = dao.getAllFriends()
    val totalReceivable: Flow<Double> = dao.getTotalReceivables()
    val totalDebt: Flow<Double> = dao.getTotalDebt()

    fun getBillPreviews(limit: Int): Flow<List<BillPreviewModel>> {
        return dao.getBillPreviews(limit)
    }

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