package com.tojare.borutoapp.data.repository

import com.tojare.borutoapp.domain.repository.DataStoreOperations
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStore: DataStoreOperations
) {

    suspend fun saveOnBoardingState(completed:Boolean){
        dataStore.saveOnBoardingState(completed)
    }

    fun readOnBoardingState() = dataStore.readOnBoardingState()
}