package com.tojare.borutoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tojare.borutoapp.domain.model.HeroRemoteKey

@Dao
interface HeroRemoteKeyDao {

    @Query("Select * From hero_remote_key_table Where id=:id")
    suspend fun getRemoteKey(id: Int): HeroRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(heroRemoteKeys: List<HeroRemoteKey>)

    @Query("Delete From hero_remote_key_table ")
    suspend fun deleteAllRemoteKeys():Int?
}