package com.afm.shopaccounts.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suppliers")
data class Supplier(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val taxNumber: String = "",
    val address: String = "",
    val phone: String = ""
)
