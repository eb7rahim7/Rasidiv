package com.afm.shopaccounts.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val barcode: String = "",
    val purchasePrice: Double = 0.0,
    val salePrice: Double = 0.0,
    val currentQty: Double = 0.0,
    val minQty: Double = 0.0,
    val unit: String = "حبة"
)
