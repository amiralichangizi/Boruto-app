package com.tojare.borutoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tojare.borutoapp.data.local.dao.HeroDao
import com.tojare.borutoapp.data.local.dao.HeroRemoteKeyDao
import com.tojare.borutoapp.domain.model.Hero
import com.tojare.borutoapp.domain.model.HeroRemoteKey

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConvertor::class)
abstract class BorutoDatabase : RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao

}