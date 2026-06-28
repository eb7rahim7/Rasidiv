package com.afm.shopaccounts.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class CashType { CASH_IN, CASH_OUT, CARD_IN, CARD_OUT }

@Entity(tableName = "cash_register")
data class CashRegister(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: CashType,
    val amount: Double,
    val date: Long = System.currentTimeMillis(),
    val reference: String = "",
    val referenceId: Long? = null,
    val notes: String = ""
)
