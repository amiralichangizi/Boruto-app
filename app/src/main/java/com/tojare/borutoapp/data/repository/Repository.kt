package com.tojare.borutoapp.data.repository

import androidx.paging.PagingData
import com.tojare.borutoapp.domain.model.Hero
import com.tojare.borutoapp.domain.repository.DataStoreOperations
import com.tojare.borutoapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStore: DataStoreOperations,
    private val remote: RemoteDataSource
) {

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed)
    }

    fun readOnBoardingState() = dataStore.readOnBoardingState()

    fun getAllHeroes():Flow<PagingData<Hero>>{
        return remote.getAllHeroes()
    }

    fun searchHeroes(query:String):Flow<PagingData<Hero>> = remote.searchHeroes(query)
}