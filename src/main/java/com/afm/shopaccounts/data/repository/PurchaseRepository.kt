package com.afm.shopaccounts.data.repository

import com.afm.shopaccounts.data.dao.*
import com.afm.shopaccounts.data.entity.*
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PurchaseRepository @Inject constructor(
    private val purchaseInvoiceDao: PurchaseInvoiceDao,
    private val purchaseInvoiceItemDao: PurchaseInvoiceItemDao,
    private val productDao: ProductDao,
    private val cashRegisterDao: CashRegisterDao
) {
    fun getAll(): Flow<List<PurchaseInvoice>> = purchaseInvoiceDao.getAll()
    suspend fun getById(id: Long): PurchaseInvoice? = purchaseInvoiceDao.getById(id)
    fun getByDateRange(startDate: Long, endDate: Long): Flow<List<PurchaseInvoice>> = purchaseInvoiceDao.getByDateRange(startDate, endDate)
    fun getTotalPurchases(): Flow<Double?> = purchaseInvoiceDao.getTotalPurchases()
    fun getItemsByInvoice(invoiceId: Long): Flow<List<PurchaseInvoiceItem>> = purchaseInvoiceItemDao.getByInvoiceId(invoiceId)

    suspend fun generateInvoiceNumber(): String {
        val year = Calendar.getInstance().get(Calendar.YEAR).toString()
        val lastNumber = purchaseInvoiceDao.getLastInvoiceNumber(year) ?: 0
        return "${year}${(lastNumber + 1).toString().padStart(6, '0')}"
    }

    suspend fun createPurchaseInvoice(
        invoice: PurchaseInvoice,
        items: List<PurchaseInvoiceItem>
    ): Long {
        val invoiceId = purchaseInvoiceDao.insert(invoice)
        val itemsWithInvoiceId = items.map { it.copy(invoiceId = invoiceId) }
        purchaseInvoiceItemDao.insertAll(itemsWithInvoiceId)

        // Increase stock for each item
        items.forEach { item ->
            productDao.increaseQty(item.productId, item.qty)
        }

        return invoiceId
    }

    suspend fun deletePurchaseInvoice(invoice: PurchaseInvoice) {
        purchaseInvoiceDao.delete(invoice)
    }
}
