package com.afm.shopaccounts.data.dao

import androidx.room.*
import com.afm.shopaccounts.data.entity.StoreInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreInfoDao {
    @Query("SELECT * FROM store_info WHERE id = 1")
    fun get(): Flow<StoreInfo?>

    @Query("SELECT * FROM store_info WHERE id = 1")
    suspend fun getSync(): StoreInfo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(storeInfo: StoreInfo)
}
