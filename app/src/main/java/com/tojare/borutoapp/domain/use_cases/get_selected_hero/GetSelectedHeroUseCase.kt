package com.tojare.borutoapp.domain.use_cases.get_selected_hero

import com.tojare.borutoapp.data.repository.Repository
import com.tojare.borutoapp.domain.model.Hero

class GetSelectedHeroUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(heroId: Int): Hero {
        return repository.getSelectedHero(heroId)
    }
}