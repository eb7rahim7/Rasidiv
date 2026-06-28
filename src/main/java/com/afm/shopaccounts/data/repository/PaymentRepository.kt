package com.afm.shopaccounts.data.repository

import com.afm.shopaccounts.data.dao.*
import com.afm.shopaccounts.data.entity.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentRepository @Inject constructor(
    private val supplierPaymentDao: SupplierPaymentDao,
    private val customerPaymentDao: CustomerPaymentDao,
    private val cashRegisterDao: CashRegisterDao
) {
    // Supplier Payments
    fun getAllSupplierPayments(): Flow<List<SupplierPayment>> = supplierPaymentDao.getAll()
    fun getSupplierPayments(supplierId: Long): Flow<List<SupplierPayment>> = supplierPaymentDao.getBySupplierId(supplierId)
    fun getTotalSupplierPayments(): Flow<Double?> = supplierPaymentDao.getTotalPayments()

    suspend fun addSupplierPayment(payment: SupplierPayment): Long {
        val id = supplierPaymentDao.insert(payment)
        cashRegisterDao.insert(
            CashRegister(
                type = CashType.CASH_OUT,
                amount = payment.amount,
                date = payment.date,
                reference = "سداد مورد",
                referenceId = id
            )
        )
        return id
    }

    // Customer Payments
    fun getAllCustomerPayments(): Flow<List<CustomerPayment>> = customerPaymentDao.getAll()
    fun getCustomerPayments(customerId: Long): Flow<List<CustomerPayment>> = customerPaymentDao.getByCustomerId(customerId)

    suspend fun addCustomerPayment(payment: CustomerPayment): Long {
        val id = customerPaymentDao.insert(payment)
        cashRegisterDao.insert(
            CashRegister(
                type = CashType.CASH_IN,
                amount = payment.amount,
                date = payment.date,
                reference = "تحصيل عميل",
                referenceId = id
            )
        )
        return id
    }
}
