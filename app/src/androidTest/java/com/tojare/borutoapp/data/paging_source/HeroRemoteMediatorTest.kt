package com.tojare.borutoapp.data.paging_source

import androidx.paging.*
import androidx.paging.RemoteMediator.*
import androidx.test.core.app.ApplicationProvider
import com.tojare.borutoapp.data.local.BorutoDatabase
import com.tojare.borutoapp.data.remote.FakeBorutoApi2
import com.tojare.borutoapp.domain.model.Hero
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class HeroRemoteMediatorTest {

    private lateinit var borutoApi: FakeBorutoApi2
    private lateinit var borutoDatabase: BorutoDatabase

    @Before
    fun setup() {
        borutoApi = FakeBorutoApi2()
        borutoDatabase = BorutoDatabase.create(
            context = ApplicationProvider.getApplicationContext(),
            useInMemmory = true
        )
    }

    @After fun cleanup(){
        borutoDatabase.clearAllTables()
    }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runBlocking {
        val remoteMediator = HeroRemoteMediator(
            borutoApi = borutoApi,
            borutoDatabase = borutoDatabase
        )
        val pagingState = PagingState<Int,Hero>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(pageSize = 3),
            leadingPlaceholderCount = 0
        )

        val result = remoteMediator.load(LoadType.REFRESH,pagingState)

        assertTrue(result is MediatorResult.Success)
        assertFalse((result as MediatorResult.Success).endOfPaginationReached)
    }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadSuccessAndEndOfPaginationTrueWhenNoMoreData() = runBlocking {
        borutoApi.clearData()
        val remoteMediator = HeroRemoteMediator(
            borutoApi = borutoApi,
            borutoDatabase = borutoDatabase
        )
        val pagingState = PagingState<Int,Hero>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(pageSize = 3),
            leadingPlaceholderCount = 0
        )

        val result = remoteMediator.load(LoadType.REFRESH,pagingState)

        assertTrue(result is MediatorResult.Success)
        assertTrue((result as MediatorResult.Success).endOfPaginationReached)
    }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadReturnsErrorWhenExceptionOccurs() = runBlocking {
        borutoApi.addException()
        val remoteMediator = HeroRemoteMediator(
            borutoApi = borutoApi,
            borutoDatabase = borutoDatabase
        )
        val pagingState = PagingState<Int,Hero>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(pageSize = 3),
            leadingPlaceholderCount = 0
        )

        val result = remoteMediator.load(LoadType.REFRESH,pagingState)

        assertTrue(result is MediatorResult.Error)
    }
}