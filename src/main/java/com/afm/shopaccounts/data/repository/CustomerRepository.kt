package com.afm.shopaccounts.data.repository

import com.afm.shopaccounts.data.dao.*
import com.afm.shopaccounts.data.entity.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomerRepository @Inject constructor(
    private val customerDao: CustomerDao,
    private val saleInvoiceDao: SaleInvoiceDao,
    private val customerPaymentDao: CustomerPaymentDao
) {
    fun getAll(): Flow<List<Customer>> = customerDao.getAll()
    fun search(query: String): Flow<List<Customer>> = customerDao.search(query)
    suspend fun getById(id: Long): Customer? = customerDao.getById(id)
    suspend fun insert(customer: Customer): Long = customerDao.insert(customer)
    suspend fun update(customer: Customer) = customerDao.update(customer)
    suspend fun delete(customer: Customer) = customerDao.delete(customer)
    fun getCount(): Flow<Int> = customerDao.getCount()
    fun getSalesByCustomer(customerId: Long): Flow<List<SaleInvoice>> = saleInvoiceDao.getByCustomerId(customerId)
    fun getPaymentsByCustomer(customerId: Long): Flow<List<CustomerPayment>> = customerPaymentDao.getByCustomerId(customerId)
    fun getTotalPaymentsByCustomer(customerId: Long): Flow<Double?> = customerPaymentDao.getTotalPaymentsByCustomerId(customerId)
}
