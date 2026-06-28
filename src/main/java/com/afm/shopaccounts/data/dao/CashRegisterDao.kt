package com.afm.shopaccounts.data.dao

import androidx.room.*
import com.afm.shopaccounts.data.entity.CashRegister
import com.afm.shopaccounts.data.entity.CashType
import kotlinx.coroutines.flow.Flow

@Dao
interface CashRegisterDao {
    @Query("SELECT * FROM cash_register ORDER BY date DESC")
    fun getAll(): Flow<List<CashRegister>>

    @Query("SELECT * FROM cash_register WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getByDateRange(startDate: Long, endDate: Long): Flow<List<CashRegister>>

    @Query("SELECT SUM(CASE WHEN type = 'CASH_IN' THEN amount ELSE 0 END) - SUM(CASE WHEN type = 'CASH_OUT' THEN amount ELSE 0 END) FROM cash_register")
    fun getCashBalance(): Flow<Double?>

    @Query("SELECT SUM(CASE WHEN type = 'CARD_IN' THEN amount ELSE 0 END) - SUM(CASE WHEN type = 'CARD_OUT' THEN amount ELSE 0 END) FROM cash_register")
    fun getCardBalance(): Flow<Double?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: CashRegister): Long

    @Delete
    suspend fun delete(entry: CashRegister)
}
