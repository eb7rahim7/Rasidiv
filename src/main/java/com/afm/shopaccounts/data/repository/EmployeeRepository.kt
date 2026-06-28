package com.afm.shopaccounts.data.repository

import com.afm.shopaccounts.data.dao.EmployeeDao
import com.afm.shopaccounts.data.dao.EmployeeTransactionDao
import com.afm.shopaccounts.data.entity.Employee
import com.afm.shopaccounts.data.entity.EmployeeTransaction
import com.afm.shopaccounts.data.entity.TransactionType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeRepository @Inject constructor(
    private val employeeDao: EmployeeDao,
    private val employeeTransactionDao: EmployeeTransactionDao
) {
    fun getAll(): Flow<List<Employee>> = employeeDao.getAll()
    suspend fun getById(id: Long): Employee? = employeeDao.getById(id)
    suspend fun insert(employee: Employee): Long = employeeDao.insert(employee)
    suspend fun update(employee: Employee) = employeeDao.update(employee)
    suspend fun delete(employee: Employee) = employeeDao.delete(employee)
    fun getTransactionsByEmployee(employeeId: Long): Flow<List<EmployeeTransaction>> = employeeTransactionDao.getByEmployeeId(employeeId)
    suspend fun addTransaction(transaction: EmployeeTransaction): Long = employeeTransactionDao.insert(transaction)
    fun getTotalByEmployeeAndType(employeeId: Long, type: TransactionType): Flow<Double?> = employeeTransactionDao.getTotalByEmployeeAndType(employeeId, type)
}
