package com.afm.shopaccounts.data.repository

import com.afm.shopaccounts.data.dao.*
import com.afm.shopaccounts.data.entity.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SupplierRepository @Inject constructor(
    private val supplierDao: SupplierDao,
    private val purchaseInvoiceDao: PurchaseInvoiceDao,
    private val supplierPaymentDao: SupplierPaymentDao
) {
    fun getAll(): Flow<List<Supplier>> = supplierDao.getAll()
    fun search(query: String): Flow<List<Supplier>> = supplierDao.search(query)
    suspend fun getById(id: Long): Supplier? = supplierDao.getById(id)
    suspend fun insert(supplier: Supplier): Long = supplierDao.insert(supplier)
    suspend fun update(supplier: Supplier) = supplierDao.update(supplier)
    suspend fun delete(supplier: Supplier) = supplierDao.delete(supplier)
    fun getCount(): Flow<Int> = supplierDao.getCount()
    fun getPurchasesBySupplier(supplierId: Long): Flow<List<PurchaseInvoice>> = purchaseInvoiceDao.getBySupplierId(supplierId)
    fun getPaymentsBySupplier(supplierId: Long): Flow<List<SupplierPayment>> = supplierPaymentDao.getBySupplierId(supplierId)
    fun getTotalPaymentsBySupplier(supplierId: Long): Flow<Double?> = supplierPaymentDao.getTotalPaymentsBySupplierId(supplierId)
}
