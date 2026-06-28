package com.afm.shopaccounts.data.dao

import androidx.room.*
import com.afm.shopaccounts.data.entity.EmployeeTransaction
import com.afm.shopaccounts.data.entity.TransactionType
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeTransactionDao {
    @Query("SELECT * FROM employee_transactions ORDER BY date DESC")
    fun getAll(): Flow<List<EmployeeTransaction>>

    @Query("SELECT * FROM employee_transactions WHERE employeeId = :employeeId ORDER BY date DESC")
    fun getByEmployeeId(employeeId: Long): Flow<List<EmployeeTransaction>>

    @Query("SELECT SUM(amount) FROM employee_transactions WHERE employeeId = :employeeId AND type = :type")
    fun getTotalByEmployeeAndType(employeeId: Long, type: TransactionType): Flow<Double?>

    @Query("SELECT SUM(amount) FROM employee_transactions WHERE employeeId = :employeeId AND type = :type AND date BETWEEN :startDate AND :endDate")
    fun getTotalByEmployeeTypeAndDateRange(employeeId: Long, type: TransactionType, startDate: Long, endDate: Long): Flow<Double?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: EmployeeTransaction): Long

    @Update
    suspend fun update(transaction: EmployeeTransaction)

    @Delete
    suspend fun delete(transaction: EmployeeTransaction)
}
