package com.afm.shopaccounts.data.database

import androidx.room.TypeConverter
import com.afm.shopaccounts.data.entity.*

class Converters {
    @TypeConverter
    fun fromPaymentMethod(value: PaymentMethod): String = value.name

    @TypeConverter
    fun toPaymentMethod(value: String): PaymentMethod = PaymentMethod.valueOf(value)

    @TypeConverter
    fun fromTransactionType(value: TransactionType): String = value.name

    @TypeConverter
    fun toTransactionType(value: String): TransactionType = TransactionType.valueOf(value)

    @TypeConverter
    fun fromCashType(value: CashType): String = value.name

    @TypeConverter
    fun toCashType(value: String): CashType = CashType.valueOf(value)

    @TypeConverter
    fun fromUserRole(value: UserRole): String = value.name

    @TypeConverter
    fun toUserRole(value: String): UserRole = UserRole.valueOf(value)
}
