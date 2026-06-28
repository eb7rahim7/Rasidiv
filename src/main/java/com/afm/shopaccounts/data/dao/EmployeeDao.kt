package com.afm.shopaccounts.data.dao

import androidx.room.*
import com.afm.shopaccounts.data.entity.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employees ORDER BY name ASC")
    fun getAll(): Flow<List<Employee>>

    @Query("SELECT * FROM employees WHERE id = :id")
    suspend fun getById(id: Long): Employee?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(employee: Employee): Long

    @Update
    suspend fun update(employee: Employee)

    @Delete
    suspend fun delete(employee: Employee)
}
