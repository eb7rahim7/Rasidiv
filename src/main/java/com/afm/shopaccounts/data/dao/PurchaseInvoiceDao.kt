package com.afm.shopaccounts.data.dao

import androidx.room.*
import com.afm.shopaccounts.data.entity.PurchaseInvoice
import com.afm.shopaccounts.data.entity.PurchaseInvoiceItem
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseInvoiceDao {
    @Query("SELECT * FROM purchase_invoices ORDER BY date DESC")
    fun getAll(): Flow<List<PurchaseInvoice>>

    @Query("SELECT * FROM purchase_invoices WHERE id = :id")
    suspend fun getById(id: Long): PurchaseInvoice?

    @Query("SELECT * FROM purchase_invoices WHERE supplierId = :supplierId ORDER BY date DESC")
    fun getBySupplierId(supplierId: Long): Flow<List<PurchaseInvoice>>

    @Query("SELECT * FROM purchase_invoices WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getByDateRange(startDate: Long, endDate: Long): Flow<List<PurchaseInvoice>>

    @Query("SELECT SUM(totalAmount) FROM purchase_invoices")
    fun getTotalPurchases(): Flow<Double?>

    @Query("SELECT SUM(totalAmount) FROM purchase_invoices WHERE date BETWEEN :startDate AND :endDate")
    fun getTotalPurchasesByDateRange(startDate: Long, endDate: Long): Flow<Double?>

    @Query("SELECT MAX(CAST(invoiceNumber AS INTEGER)) FROM purchase_invoices WHERE invoiceNumber LIKE :yearPrefix || '%'")
    suspend fun getLastInvoiceNumber(yearPrefix: String): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(invoice: PurchaseInvoice): Long

    @Update
    suspend fun update(invoice: PurchaseInvoice)

    @Delete
    suspend fun delete(invoice: PurchaseInvoice)
}
