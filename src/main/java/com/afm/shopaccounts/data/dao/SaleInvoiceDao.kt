package com.afm.shopaccounts.data.dao

import androidx.room.*
import com.afm.shopaccounts.data.entity.SaleInvoice
import kotlinx.coroutines.flow.Flow

@Dao
interface SaleInvoiceDao {
    @Query("SELECT * FROM sale_invoices ORDER BY date DESC")
    fun getAll(): Flow<List<SaleInvoice>>

    @Query("SELECT * FROM sale_invoices WHERE id = :id")
    suspend fun getById(id: Long): SaleInvoice?

    @Query("SELECT * FROM sale_invoices WHERE customerId = :customerId ORDER BY date DESC")
    fun getByCustomerId(customerId: Long): Flow<List<SaleInvoice>>

    @Query("SELECT * FROM sale_invoices WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getByDateRange(startDate: Long, endDate: Long): Flow<List<SaleInvoice>>

    @Query("SELECT SUM(totalAmount) FROM sale_invoices")
    fun getTotalSales(): Flow<Double?>

    @Query("SELECT SUM(totalAmount) FROM sale_invoices WHERE date BETWEEN :startDate AND :endDate")
    fun getTotalSalesByDateRange(startDate: Long, endDate: Long): Flow<Double?>

    @Query("SELECT * FROM sale_invoices WHERE isCredit = 1 ORDER BY date DESC")
    fun getCreditSales(): Flow<List<SaleInvoice>>

    @Query("SELECT MAX(CAST(invoiceNumber AS INTEGER)) FROM sale_invoices WHERE invoiceNumber LIKE :yearPrefix || '%'")
    suspend fun getLastInvoiceNumber(yearPrefix: String): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(invoice: SaleInvoice): Long

    @Update
    suspend fun update(invoice: SaleInvoice)

    @Delete
    suspend fun delete(invoice: SaleInvoice)
}
