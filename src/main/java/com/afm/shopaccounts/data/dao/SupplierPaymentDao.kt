package com.afm.shopaccounts.data.dao

import androidx.room.*
import com.afm.shopaccounts.data.entity.SupplierPayment
import kotlinx.coroutines.flow.Flow

@Dao
interface SupplierPaymentDao {
    @Query("SELECT * FROM supplier_payments ORDER BY date DESC")
    fun getAll(): Flow<List<SupplierPayment>>

    @Query("SELECT * FROM supplier_payments WHERE supplierId = :supplierId ORDER BY date DESC")
    fun getBySupplierId(supplierId: Long): Flow<List<SupplierPayment>>

    @Query("SELECT SUM(amount) FROM supplier_payments WHERE supplierId = :supplierId")
    fun getTotalPaymentsBySupplierId(supplierId: Long): Flow<Double?>

    @Query("SELECT SUM(amount) FROM supplier_payments")
    fun getTotalPayments(): Flow<Double?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(payment: SupplierPayment): Long

    @Update
    suspend fun update(payment: SupplierPayment)

    @Delete
    suspend fun delete(payment: SupplierPayment)
}
