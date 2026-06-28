package com.afm.shopaccounts.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "customer_payments",
    foreignKeys = [ForeignKey(
        entity = Customer::class,
        parentColumns = ["id"],
        childColumns = ["customerId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("customerId")]
)
data class CustomerPayment(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val customerId: Long,
    val amount: Double,
    val date: Long = System.currentTimeMillis(),
    val notes: String = ""
)
