package com.tojare.borutoapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tojare.borutoapp.domain.model.Hero

@Dao
interface HeroDao {

    @Query("Select * From hero_table Order by id ASC")
    fun getAllHeroes(): PagingSource<Int, Hero>

    @Query("Select * From hero_table Where id=:heroId")
    fun getSelectedHero(heroId: Int): Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHeroes(heroes: List<Hero>)

    @Query("Delete From hero_table")
    suspend fun deleteAllHeroes():Int?
}