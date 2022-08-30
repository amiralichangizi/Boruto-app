package com.tojare.borutoapp.data.paging_source

import androidx.paging.PagingSource
import com.tojare.borutoapp.data.remote.BorutoApi
import com.tojare.borutoapp.data.remote.FakeBorutoApi
import com.tojare.borutoapp.domain.model.Hero
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SearchHeroesSourceTest {

    private lateinit var borutoApi: BorutoApi
    private lateinit var heroes:List<Hero>

    @Before
    fun setup(){
        borutoApi = FakeBorutoApi()
        heroes = FakeBorutoApi.heroes

    }

    @Test
    fun `Search api with existing name, expect single hero result, assert LoadResult Page`() = runBlocking {
        val heroSource = SearchHeroSource(borutoApi = borutoApi,"Sasuke")
       assertEquals<PagingSource.LoadResult<Int,Hero>>(
            expected = PagingSource.LoadResult.Page(
                data = listOf(heroes.first()),
                prevKey = null,
                nextKey = null
            ),
            actual = heroSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )
        )

    }

    @Test
    fun `Search api with existing name, expect 2 hero result, assert LoadResult Page`() = runBlocking {
        val heroSource = SearchHeroSource(borutoApi = borutoApi,"Sa")
        assertEquals<PagingSource.LoadResult<Int,Hero>>(
            expected = PagingSource.LoadResult.Page(
                data = listOf(heroes.first(),heroes[2]),
                prevKey = null,
                nextKey = null
            ),
            actual = heroSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )
        )

    }

    @Test
    fun `Search api with empty name, expect 0 hero result, assert LoadResult Page`() = runBlocking {
        val heroSource = SearchHeroSource(borutoApi = borutoApi,"")

        val loadResult=heroSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 3,
                placeholdersEnabled = false
            )
        )

        val result = borutoApi.searchHeroes("").heroes

        assertTrue { result.isEmpty() }
        assertTrue { loadResult is PagingSource.LoadResult.Page }


    }

    @Test
    fun `Search api with none-existing name, expect 0 hero result, assert LoadResult Page`() = runBlocking {
        val heroSource = SearchHeroSource(borutoApi = borutoApi,"Unknown")

        val loadResult=heroSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 3,
                placeholdersEnabled = false
            )
        )

        val result = borutoApi.searchHeroes("Unknown").heroes

        assertTrue { result.isEmpty() }
        assertTrue { loadResult is PagingSource.LoadResult.Page }


    }


}