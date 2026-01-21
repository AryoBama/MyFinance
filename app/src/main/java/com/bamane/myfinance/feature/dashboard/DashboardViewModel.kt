package com.bamane.myfinance.feature.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bamane.myfinance.MyApplication
import com.bamane.myfinance.core.data.FinanceRepository
import com.bamane.myfinance.core.database.entity.PersonEntity
import com.bamane.myfinance.core.model.BillPreviewModel
import com.bamane.myfinance.feature.friend.FriendViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

const val BILL_LIMIT = 3
class DashboardViewModel(private val repository: FinanceRepository): ViewModel() {

    private val _userName = MutableStateFlow("User")
    val userName: StateFlow<String> = _userName

    init {
        viewModelScope.launch {
            _userName.value = repository.getMyProfile()?.name ?: "User"
        }
    }
    val totalReceivable: StateFlow<Double> = repository.totalReceivable
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

    val totalDebt: StateFlow<Double> = repository.totalDebt
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

    val totalBalance: StateFlow<Double> =
        combine(totalReceivable, totalDebt) { receivable, debt ->
            receivable - debt
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

    val billPreviews: StateFlow<List<BillPreviewModel>> = repository.getBillPreviews(BILL_LIMIT)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val repository = application.repository
                DashboardViewModel(repository)
            }
        }
    }
}