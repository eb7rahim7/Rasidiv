package com.afm.shopaccounts.data.dao

import androidx.room.*
import com.afm.shopaccounts.data.entity.AppUser
import com.afm.shopaccounts.data.entity.UserRole
import kotlinx.coroutines.flow.Flow

@Dao
interface AppUserDao {
    @Query("SELECT * FROM app_users ORDER BY name ASC")
    fun getAll(): Flow<List<AppUser>>

    @Query("SELECT * FROM app_users WHERE id = :id")
    suspend fun getById(id: Long): AppUser?

    @Query("SELECT * FROM app_users WHERE pinCode = :pin LIMIT 1")
    suspend fun getByPin(pin: String): AppUser?

    @Query("SELECT * FROM app_users WHERE role = :role")
    fun getByRole(role: UserRole): Flow<List<AppUser>>

    @Query("SELECT COUNT(*) FROM app_users")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: AppUser): Long

    @Update
    suspend fun update(user: AppUser)

    @Delete
    suspend fun delete(user: AppUser)
}
