package com.afm.shopaccounts.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "purchase_invoices",
    foreignKeys = [ForeignKey(
        entity = Supplier::class,
        parentColumns = ["id"],
        childColumns = ["supplierId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("supplierId")]
)
data class PurchaseInvoice(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val supplierId: Long,
    val invoiceNumber: String,
    val date: Long = System.currentTimeMillis(),
    val totalAmount: Double = 0.0,
    val taxIncluded: Boolean = false,
    val taxAmount: Double = 0.0
)
