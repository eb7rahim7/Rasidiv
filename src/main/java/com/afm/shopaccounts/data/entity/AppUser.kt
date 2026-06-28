package com.afm.shopaccounts.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class UserRole { ADMIN, CASHIER }

@Entity(tableName = "app_users")
data class AppUser(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val role: UserRole = UserRole.CASHIER,
    val pinCode: String = ""
)
