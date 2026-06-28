package com.afm.shopaccounts.data.repository

import com.afm.shopaccounts.data.dao.ExpenseCategoryDao
import com.afm.shopaccounts.data.dao.ExpenseDao
import com.afm.shopaccounts.data.entity.Expense
import com.afm.shopaccounts.data.entity.ExpenseCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExpenseRepository @Inject constructor(
    private val expenseDao: ExpenseDao,
    private val expenseCategoryDao: ExpenseCategoryDao
) {
    fun getAll(): Flow<List<Expense>> = expenseDao.getAll()
    fun getByDateRange(startDate: Long, endDate: Long): Flow<List<Expense>> = expenseDao.getByDateRange(startDate, endDate)
    fun getByCategoryId(categoryId: Long): Flow<List<Expense>> = expenseDao.getByCategoryId(categoryId)
    fun getTotalExpenses(): Flow<Double?> = expenseDao.getTotalExpenses()
    fun getTotalExpensesByDateRange(startDate: Long, endDate: Long): Flow<Double?> = expenseDao.getTotalExpensesByDateRange(startDate, endDate)
    suspend fun getById(id: Long): Expense? = expenseDao.getById(id)
    suspend fun insert(expense: Expense): Long = expenseDao.insert(expense)
    suspend fun update(expense: Expense) = expenseDao.update(expense)
    suspend fun delete(expense: Expense) = expenseDao.delete(expense)

    fun getAllCategories(): Flow<List<ExpenseCategory>> = expenseCategoryDao.getAll()
    suspend fun getCategoryById(id: Long): ExpenseCategory? = expenseCategoryDao.getById(id)
    suspend fun insertCategory(category: ExpenseCategory): Long = expenseCategoryDao.insert(category)
    suspend fun updateCategory(category: ExpenseCategory) = expenseCategoryDao.update(category)
    suspend fun deleteCategory(category: ExpenseCategory) = expenseCategoryDao.delete(category)
}
