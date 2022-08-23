package com.tojare.borutoapp.domain.repository

import com.tojare.borutoapp.domain.model.Hero

interface LocalDataSource {

    suspend fun getSelectedHero(heroId: Int): Hero
}