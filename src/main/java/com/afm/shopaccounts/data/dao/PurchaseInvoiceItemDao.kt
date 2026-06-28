package com.afm.shopaccounts.data.dao

import androidx.room.*
import com.afm.shopaccounts.data.entity.PurchaseInvoiceItem
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseInvoiceItemDao {
    @Query("SELECT * FROM purchase_invoice_items WHERE invoiceId = :invoiceId")
    fun getByInvoiceId(invoiceId: Long): Flow<List<PurchaseInvoiceItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: PurchaseInvoiceItem): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<PurchaseInvoiceItem>)

    @Delete
    suspend fun delete(item: PurchaseInvoiceItem)

    @Query("DELETE FROM purchase_invoice_items WHERE invoiceId = :invoiceId")
    suspend fun deleteByInvoiceId(invoiceId: Long)
}
