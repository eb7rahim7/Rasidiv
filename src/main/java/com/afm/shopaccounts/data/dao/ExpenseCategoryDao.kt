package com.afm.shopaccounts.data.dao

import androidx.room.*
import com.afm.shopaccounts.data.entity.ExpenseCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseCategoryDao {
    @Query("SELECT * FROM expense_categories ORDER BY name ASC")
    fun getAll(): Flow<List<ExpenseCategory>>

    @Query("SELECT * FROM expense_categories WHERE id = :id")
    suspend fun getById(id: Long): ExpenseCategory?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: ExpenseCategory): Long

    @Update
    suspend fun update(category: ExpenseCategory)

    @Delete
    suspend fun delete(category: ExpenseCategory)
}
