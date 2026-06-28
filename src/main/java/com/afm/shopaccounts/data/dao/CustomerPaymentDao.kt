package com.afm.shopaccounts.data.dao

import androidx.room.*
import com.afm.shopaccounts.data.entity.CustomerPayment
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerPaymentDao {
    @Query("SELECT * FROM customer_payments ORDER BY date DESC")
    fun getAll(): Flow<List<CustomerPayment>>

    @Query("SELECT * FROM customer_payments WHERE customerId = :customerId ORDER BY date DESC")
    fun getByCustomerId(customerId: Long): Flow<List<CustomerPayment>>

    @Query("SELECT SUM(amount) FROM customer_payments WHERE customerId = :customerId")
    fun getTotalPaymentsByCustomerId(customerId: Long): Flow<Double?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(payment: CustomerPayment): Long

    @Update
    suspend fun update(payment: CustomerPayment)

    @Delete
    suspend fun delete(payment: CustomerPayment)
}
