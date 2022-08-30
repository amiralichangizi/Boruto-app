package com.tojare.borutoapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tojare.borutoapp.data.local.dao.HeroDao
import com.tojare.borutoapp.data.local.dao.HeroRemoteKeysDao
import com.tojare.borutoapp.domain.model.Hero
import com.tojare.borutoapp.domain.model.HeroRemoteKeys

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 2, exportSchema = false)
@TypeConverters(DatabaseConvertor::class)
abstract class BorutoDatabase : RoomDatabase() {

    companion object{
        fun create(context: Context,useInMemmory:Boolean):BorutoDatabase{
            val databaseBuilder = if (useInMemmory){
                Room.inMemoryDatabaseBuilder(context,BorutoDatabase::class.java)
            }else{
                Room.databaseBuilder(context,BorutoDatabase::class.java,"test_boruto_database.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeysDao

}