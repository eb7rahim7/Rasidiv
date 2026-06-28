package com.afm.shopaccounts.data.repository

import com.afm.shopaccounts.data.dao.*
import com.afm.shopaccounts.data.entity.*
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaleRepository @Inject constructor(
    private val saleInvoiceDao: SaleInvoiceDao,
    private val saleInvoiceItemDao: SaleInvoiceItemDao,
    private val productDao: ProductDao,
    private val cashRegisterDao: CashRegisterDao
) {
    fun getAll(): Flow<List<SaleInvoice>> = saleInvoiceDao.getAll()
    suspend fun getById(id: Long): SaleInvoice? = saleInvoiceDao.getById(id)
    fun getByDateRange(startDate: Long, endDate: Long): Flow<List<SaleInvoice>> = saleInvoiceDao.getByDateRange(startDate, endDate)
    fun getTotalSales(): Flow<Double?> = saleInvoiceDao.getTotalSales()
    fun getTotalSalesByDateRange(startDate: Long, endDate: Long): Flow<Double?> = saleInvoiceDao.getTotalSalesByDateRange(startDate, endDate)
    fun getTotalProfit(): Flow<Double?> = saleInvoiceItemDao.getTotalProfit()
    fun getTotalProfitByDateRange(startDate: Long, endDate: Long): Flow<Double?> = saleInvoiceItemDao.getTotalProfitByDateRange(startDate, endDate)
    fun getItemsByInvoice(invoiceId: Long): Flow<List<SaleInvoiceItem>> = saleInvoiceItemDao.getByInvoiceId(invoiceId)
    fun getCreditSales(): Flow<List<SaleInvoice>> = saleInvoiceDao.getCreditSales()

    suspend fun generateInvoiceNumber(): String {
        val year = Calendar.getInstance().get(Calendar.YEAR).toString()
        val lastNumber = saleInvoiceDao.getLastInvoiceNumber(year) ?: 0
        return "${year}${(lastNumber + 1).toString().padStart(6, '0')}"
    }

    suspend fun createSaleInvoice(
        invoice: SaleInvoice,
        items: List<SaleInvoiceItem>
    ): Long {
        val invoiceId = saleInvoiceDao.insert(invoice)
        val itemsWithInvoiceId = items.map { it.copy(invoiceId = invoiceId) }
        saleInvoiceItemDao.insertAll(itemsWithInvoiceId)

        // Decrease stock for each item
        items.forEach { item ->
            productDao.decreaseQty(item.productId, item.qty)
        }

        // Record cash register entries
        if (invoice.cashAmount > 0) {
            cashRegisterDao.insert(
                CashRegister(
                    type = CashType.CASH_IN,
                    amount = invoice.cashAmount,
                    date = invoice.date,
                    reference = "فاتورة بيع",
                    referenceId = invoiceId
                )
            )
        }
        if (invoice.cardAmount > 0) {
            cashRegisterDao.insert(
                CashRegister(
                    type = CashType.CARD_IN,
                    amount = invoice.cardAmount,
                    date = invoice.date,
                    reference = "فاتورة بيع",
                    referenceId = invoiceId
                )
            )
        }

        return invoiceId
    }

    suspend fun deleteSaleInvoice(invoice: SaleInvoice) {
        saleInvoiceDao.delete(invoice)
    }
}
