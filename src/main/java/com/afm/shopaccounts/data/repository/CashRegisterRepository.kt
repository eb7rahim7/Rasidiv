package com.afm.shopaccounts.data.repository

import com.afm.shopaccounts.data.dao.CashRegisterDao
import com.afm.shopaccounts.data.entity.CashRegister
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CashRegisterRepository @Inject constructor(
    private val cashRegisterDao: CashRegisterDao
) {
    fun getAll(): Flow<List<CashRegister>> = cashRegisterDao.getAll()
    fun getByDateRange(startDate: Long, endDate: Long): Flow<List<CashRegister>> = cashRegisterDao.getByDateRange(startDate, endDate)
    fun getCashBalance(): Flow<Double?> = cashRegisterDao.getCashBalance()
    fun getCardBalance(): Flow<Double?> = cashRegisterDao.getCardBalance()
    suspend fun insert(entry: CashRegister): Long = cashRegisterDao.insert(entry)
    suspend fun delete(entry: CashRegister) = cashRegisterDao.delete(entry)
}
