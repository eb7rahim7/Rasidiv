package com.afm.shopaccounts.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

enum class PaymentMethod { CASH, CARD, MIXED }

@Entity(
    tableName = "sale_invoices",
    foreignKeys = [ForeignKey(
        entity = Customer::class,
        parentColumns = ["id"],
        childColumns = ["customerId"],
        onDelete = ForeignKey.SET_NULL
    )],
    indices = [Index("customerId")]
)
data class SaleInvoice(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val customerId: Long? = null,
    val invoiceNumber: String,
    val date: Long = System.currentTimeMillis(),
    val totalAmount: Double = 0.0,
    val discount: Double = 0.0,
    val taxIncluded: Boolean = false,
    val taxAmount: Double = 0.0,
    val paymentMethod: PaymentMethod = PaymentMethod.CASH,
    val cashAmount: Double = 0.0,
    val cardAmount: Double = 0.0,
    val cashierId: Long? = null,
    val isCredit: Boolean = false
)
