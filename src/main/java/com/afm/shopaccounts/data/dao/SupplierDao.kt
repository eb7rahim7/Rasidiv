package com.afm.shopaccounts.data.dao

import androidx.room.*
import com.afm.shopaccounts.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

@Dao
interface SupplierDao {
    @Query("SELECT * FROM suppliers ORDER BY name ASC")
    fun getAll(): Flow<List<Supplier>>

    @Query("SELECT * FROM suppliers WHERE id = :id")
    suspend fun getById(id: Long): Supplier?

    @Query("SELECT * FROM suppliers WHERE name LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<Supplier>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(supplier: Supplier): Long

    @Update
    suspend fun update(supplier: Supplier)

    @Delete
    suspend fun delete(supplier: Supplier)

    @Query("SELECT COUNT(*) FROM suppliers")
    fun getCount(): Flow<Int>
}
