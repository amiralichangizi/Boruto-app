package com.tojare.borutoapp.domain.use_cases

import com.tojare.borutoapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCases
import com.tojare.borutoapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.tojare.borutoapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCases: GetAllHeroesUseCases
)
