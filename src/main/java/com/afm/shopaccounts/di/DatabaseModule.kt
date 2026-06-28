package com.afm.shopaccounts.di

import android.content.Context
import androidx.room.Room
import com.afm.shopaccounts.data.database.AppDatabase
import com.afm.shopaccounts.data.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "shop_accounts_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides fun provideSupplierDao(db: AppDatabase): SupplierDao = db.supplierDao()
    @Provides fun provideProductDao(db: AppDatabase): ProductDao = db.productDao()
    @Provides fun provideCustomerDao(db: AppDatabase): CustomerDao = db.customerDao()
    @Provides fun provideEmployeeDao(db: AppDatabase): EmployeeDao = db.employeeDao()
    @Provides fun providePurchaseInvoiceDao(db: AppDatabase): PurchaseInvoiceDao = db.purchaseInvoiceDao()
    @Provides fun providePurchaseInvoiceItemDao(db: AppDatabase): PurchaseInvoiceItemDao = db.purchaseInvoiceItemDao()
    @Provides fun provideSaleInvoiceDao(db: AppDatabase): SaleInvoiceDao = db.saleInvoiceDao()
    @Provides fun provideSaleInvoiceItemDao(db: AppDatabase): SaleInvoiceItemDao = db.saleInvoiceItemDao()
    @Provides fun provideSupplierPaymentDao(db: AppDatabase): SupplierPaymentDao = db.supplierPaymentDao()
    @Provides fun provideCustomerPaymentDao(db: AppDatabase): CustomerPaymentDao = db.customerPaymentDao()
    @Provides fun provideExpenseCategoryDao(db: AppDatabase): ExpenseCategoryDao = db.expenseCategoryDao()
    @Provides fun provideExpenseDao(db: AppDatabase): ExpenseDao = db.expenseDao()
    @Provides fun provideEmployeeTransactionDao(db: AppDatabase): EmployeeTransactionDao = db.employeeTransactionDao()
    @Provides fun provideCashRegisterDao(db: AppDatabase): CashRegisterDao = db.cashRegisterDao()
    @Provides fun provideStoreInfoDao(db: AppDatabase): StoreInfoDao = db.storeInfoDao()
    @Provides fun provideAppUserDao(db: AppDatabase): AppUserDao = db.appUserDao()
}
