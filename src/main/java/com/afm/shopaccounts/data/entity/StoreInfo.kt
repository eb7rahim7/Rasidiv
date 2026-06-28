package com.afm.shopaccounts.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "store_info")
data class StoreInfo(
    @PrimaryKey val id: Long = 1,
    val storeName: String = "المحل",
    val logo: String = "",
    val address: String = "",
    val phone: String = "",
    val taxNumber: String = ""
)
