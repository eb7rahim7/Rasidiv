package com.afm.shopaccounts.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "purchase_invoice_items",
    foreignKeys = [ForeignKey(
        entity = PurchaseInvoice::class,
        parentColumns = ["id"],
        childColumns = ["invoiceId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("invoiceId")]
)
data class PurchaseInvoiceItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val invoiceId: Long,
    val productId: Long,
    val productName: String,
    val qty: Double,
    val price: Double,
    val total: Double
)
