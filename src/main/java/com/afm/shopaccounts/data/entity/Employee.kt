package com.afm.shopaccounts.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
data class Employee(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val salary: Double = 0.0,
    val allowance: Double = 0.0,
    val profession: String = "",
    val notes: String = ""
)
