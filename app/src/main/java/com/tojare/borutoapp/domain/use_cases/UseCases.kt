package com.tojare.borutoapp.domain.use_cases

import com.tojare.borutoapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCases
import com.tojare.borutoapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.tojare.borutoapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.tojare.borutoapp.domain.use_cases.search_heroes.SearchHeroesUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCases: GetAllHeroesUseCases,
    val searchHeroesUseCase: SearchHeroesUseCase
)
