package com.afm.shopaccounts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.afm.shopaccounts.data.dao.*
import com.afm.shopaccounts.data.entity.*

@Database(
    entities = [
        Supplier::class,
        Product::class,
        Customer::class,
        Employee::class,
        PurchaseInvoice::class,
        PurchaseInvoiceItem::class,
        SaleInvoice::class,
        SaleInvoiceItem::class,
        SupplierPayment::class,
        CustomerPayment::class,
        ExpenseCategory::class,
        Expense::class,
        EmployeeTransaction::class,
        CashRegister::class,
        StoreInfo::class,
        AppUser::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun supplierDao(): SupplierDao
    abstract fun productDao(): ProductDao
    abstract fun customerDao(): CustomerDao
    abstract fun employeeDao(): EmployeeDao
    abstract fun purchaseInvoiceDao(): PurchaseInvoiceDao
    abstract fun purchaseInvoiceItemDao(): PurchaseInvoiceItemDao
    abstract fun saleInvoiceDao(): SaleInvoiceDao
    abstract fun saleInvoiceItemDao(): SaleInvoiceItemDao
    abstract fun supplierPaymentDao(): SupplierPaymentDao
    abstract fun customerPaymentDao(): CustomerPaymentDao
    abstract fun expenseCategoryDao(): ExpenseCategoryDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun employeeTransactionDao(): EmployeeTransactionDao
    abstract fun cashRegisterDao(): CashRegisterDao
    abstract fun storeInfoDao(): StoreInfoDao
    abstract fun appUserDao(): AppUserDao
}
