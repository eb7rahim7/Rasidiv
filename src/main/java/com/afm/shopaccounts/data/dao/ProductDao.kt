package com.afm.shopaccounts.data.dao

import androidx.room.*
import com.afm.shopaccounts.data.entity.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products ORDER BY name ASC")
    fun getAll(): Flow<List<Product>>

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getById(id: Long): Product?

    @Query("SELECT * FROM products WHERE name LIKE '%' || :query || '%' OR barcode LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<Product>>

    @Query("SELECT * FROM products WHERE currentQty <= minQty")
    fun getLowStock(): Flow<List<Product>>

    @Query("UPDATE products SET currentQty = currentQty + :qty WHERE id = :productId")
    suspend fun increaseQty(productId: Long, qty: Double)

    @Query("UPDATE products SET currentQty = currentQty - :qty WHERE id = :productId")
    suspend fun decreaseQty(productId: Long, qty: Double)

    @Query("SELECT SUM(currentQty * purchasePrice) FROM products")
    fun getTotalStockValue(): Flow<Double?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product): Long

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Query("SELECT * FROM products WHERE barcode = :barcode LIMIT 1")
    suspend fun getByBarcode(barcode: String): Product?
}
