package com.afm.shopaccounts.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

enum class TransactionType { DEDUCTION, ALLOWANCE, SALARY }

@Entity(
    tableName = "employee_transactions",
    foreignKeys = [ForeignKey(
        entity = Employee::class,
        parentColumns = ["id"],
        childColumns = ["employeeId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("employeeId")]
)
data class EmployeeTransaction(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val employeeId: Long,
    val type: TransactionType,
    val amount: Double,
    val date: Long = System.currentTimeMillis(),
    val notes: String = ""
)
