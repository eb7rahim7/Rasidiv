package com.afm.shopaccounts.data.repository

import com.afm.shopaccounts.data.dao.AppUserDao
import com.afm.shopaccounts.data.dao.StoreInfoDao
import com.afm.shopaccounts.data.entity.AppUser
import com.afm.shopaccounts.data.entity.StoreInfo
import com.afm.shopaccounts.data.entity.UserRole
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreRepository @Inject constructor(
    private val storeInfoDao: StoreInfoDao,
    private val appUserDao: AppUserDao
) {
    fun getStoreInfo(): Flow<StoreInfo?> = storeInfoDao.get()
    suspend fun getStoreInfoSync(): StoreInfo? = storeInfoDao.getSync()
    suspend fun updateStoreInfo(storeInfo: StoreInfo) = storeInfoDao.insertOrUpdate(storeInfo)

    fun getAllUsers(): Flow<List<AppUser>> = appUserDao.getAll()
    fun getCashiers(): Flow<List<AppUser>> = appUserDao.getByRole(UserRole.CASHIER)
    suspend fun getUserById(id: Long): AppUser? = appUserDao.getById(id)
    suspend fun getUserByPin(pin: String): AppUser? = appUserDao.getByPin(pin)
    suspend fun getUserCount(): Int = appUserDao.getCount()
    suspend fun insertUser(user: AppUser): Long = appUserDao.insert(user)
    suspend fun updateUser(user: AppUser) = appUserDao.update(user)
    suspend fun deleteUser(user: AppUser) = appUserDao.delete(user)
}
