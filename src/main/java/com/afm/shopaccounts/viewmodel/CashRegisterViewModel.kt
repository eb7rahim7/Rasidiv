package com.afm.shopaccounts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afm.shopaccounts.data.entity.CashRegister
import com.afm.shopaccounts.data.repository.CashRegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CashRegisterViewModel @Inject constructor(
    private val cashRegisterRepository: CashRegisterRepository
) : ViewModel() {

    val entries: StateFlow<List<CashRegister>> = cashRegisterRepository.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val cashBalance: StateFlow<Double> = cashRegisterRepository.getCashBalance()
        .map { it ?: 0.0 }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    val cardBalance: StateFlow<Double> = cashRegisterRepository.getCardBalance()
        .map { it ?: 0.0 }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    private val _startDate = MutableStateFlow<Long?>(null)
    private val _endDate = MutableStateFlow<Long?>(null)

    val filteredEntries: StateFlow<List<CashRegister>> = combine(_startDate, _endDate) { start, end ->
        Pair(start, end)
    }.flatMapLatest { (start, end) ->
        if (start != null && end != null) {
            cashRegisterRepository.getByDateRange(start, end)
        } else {
            cashRegisterRepository.getAll()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setDateRange(start: Long?, end: Long?) {
        _startDate.value = start
        _endDate.value = end
    }
}
