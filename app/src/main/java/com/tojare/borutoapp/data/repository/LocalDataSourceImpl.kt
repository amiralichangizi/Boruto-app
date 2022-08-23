package com.tojare.borutoapp.data.repository

import com.tojare.borutoapp.data.local.BorutoDatabase
import com.tojare.borutoapp.domain.model.Hero
import com.tojare.borutoapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(
    borutoDatabase: BorutoDatabase
) : LocalDataSource {

    private val heroDao = borutoDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId)
    }
}