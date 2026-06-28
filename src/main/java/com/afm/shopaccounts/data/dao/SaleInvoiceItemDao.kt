package com.afm.shopaccounts.data.dao

import androidx.room.*
import com.afm.shopaccounts.data.entity.SaleInvoiceItem
import kotlinx.coroutines.flow.Flow

@Dao
interface SaleInvoiceItemDao {
    @Query("SELECT * FROM sale_invoice_items WHERE invoiceId = :invoiceId")
    fun getByInvoiceId(invoiceId: Long): Flow<List<SaleInvoiceItem>>

    @Query("SELECT SUM(profit) FROM sale_invoice_items si INNER JOIN sale_invoices s ON si.invoiceId = s.id")
    fun getTotalProfit(): Flow<Double?>

    @Query("SELECT SUM(profit) FROM sale_invoice_items si INNER JOIN sale_invoices s ON si.invoiceId = s.id WHERE s.date BETWEEN :startDate AND :endDate")
    fun getTotalProfitByDateRange(startDate: Long, endDate: Long): Flow<Double?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: SaleInvoiceItem): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<SaleInvoiceItem>)

    @Delete
    suspend fun delete(item: SaleInvoiceItem)

    @Query("DELETE FROM sale_invoice_items WHERE invoiceId = :invoiceId")
    suspend fun deleteByInvoiceId(invoiceId: Long)
}
