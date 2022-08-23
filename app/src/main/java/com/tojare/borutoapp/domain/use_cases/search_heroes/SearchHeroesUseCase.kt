package com.tojare.borutoapp.domain.use_cases.search_heroes

import androidx.paging.PagingData
import com.tojare.borutoapp.data.repository.Repository
import com.tojare.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(query:String):Flow<PagingData<Hero>>{
        return repository.searchHeroes(query)
    }
}