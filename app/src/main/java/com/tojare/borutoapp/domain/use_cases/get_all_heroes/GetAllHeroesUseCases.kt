package com.tojare.borutoapp.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.tojare.borutoapp.data.repository.Repository
import com.tojare.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCases(
    private val repository: Repository
) {
    operator fun invoke():Flow<PagingData<Hero>> = repository.getAllHeroes()
}