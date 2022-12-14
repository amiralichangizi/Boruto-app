package com.tojare.borutoapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import com.tojare.borutoapp.data.local.BorutoDatabase
import com.tojare.borutoapp.data.repository.LocalDataSourceImpl
import com.tojare.borutoapp.domain.repository.LocalDataSource
import com.tojare.borutoapp.util.Constant.BORUTO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): BorutoDatabase {
        return Room.databaseBuilder(
            context,
            BorutoDatabase::class.java,
            BORUTO_DATABASE
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: BorutoDatabase
    ):LocalDataSource {
        return LocalDataSourceImpl(database)
    }
}