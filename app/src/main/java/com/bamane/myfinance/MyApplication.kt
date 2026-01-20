package com.bamane.myfinance

import android.app.Application
import com.bamane.myfinance.core.data.FinanceRepository
import com.bamane.myfinance.core.database.dao.FinanceDao
import com.bamane.myfinance.core.database.AppDatabase

class MyApplication: Application() {
    private val database by lazy { AppDatabase.getDatabase(this) }

    val repository by lazy { FinanceRepository(database.financeDao()) }
}