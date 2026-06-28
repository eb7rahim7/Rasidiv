package com.afm.shopaccounts.data.repository

import com.afm.shopaccounts.data.dao.ProductDao
import com.afm.shopaccounts.data.entity.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val productDao: ProductDao
) {
    fun getAll(): Flow<List<Product>> = productDao.getAll()
    fun search(query: String): Flow<List<Product>> = productDao.search(query)
    fun getLowStock(): Flow<List<Product>> = productDao.getLowStock()
    fun getTotalStockValue(): Flow<Double?> = productDao.getTotalStockValue()
    suspend fun getById(id: Long): Product? = productDao.getById(id)
    suspend fun getByBarcode(barcode: String): Product? = productDao.getByBarcode(barcode)
    suspend fun insert(product: Product): Long = productDao.insert(product)
    suspend fun update(product: Product) = productDao.update(product)
    suspend fun delete(product: Product) = productDao.delete(product)
    suspend fun increaseQty(productId: Long, qty: Double) = productDao.increaseQty(productId, qty)
    suspend fun decreaseQty(productId: Long, qty: Double) = productDao.decreaseQty(productId, qty)
}
