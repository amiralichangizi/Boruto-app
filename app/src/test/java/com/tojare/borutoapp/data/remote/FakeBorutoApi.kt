package com.tojare.borutoapp.data.remote

import com.tojare.borutoapp.domain.model.ApiResponse
import com.tojare.borutoapp.domain.model.Hero

class FakeBorutoApi : BorutoApi {

  companion object{
      val heroes = listOf(
          Hero(
              id = 1,
              name = "Sasuke",
              image = "/images/sasuke.jpg",
              about = "Sasuke Uchiha is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
              rating = 5.0,
              power = 98,
              month = "July",
              day = "23rd",
              family = listOf(
                  "Fugaku",
                  "Mikoto",
                  "Itachi",
                  "Sarada",
                  "Sakura"
              ),
              abilities = listOf(
                  "Sharingan",
                  "Rinnegan",
                  "Intelligence"
              ),
              natureTypes = listOf(
                  "Lightning",
                  "Fire",
                  "Wind",
                  "Earth",
                  "Water"
              )
          ),
          Hero(
              id = 2,
              name = "Naruto",
              image = "/images/naruto.jpg",
              about = "Naruto Uzumaki is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
              rating = 5.0,
              power = 98,
              month = "Oct",
              day = "10th ",
              family = listOf(
                  "Minato",
                  "Kushina",
                  "Bortuo",
                  "Himawari",
                  "Hinata"
              ),
              abilities = listOf(
                  "Rasengan",
                  "Rasen-Shuriken",
                  "Senin Mode"
              ),
              natureTypes = listOf(
                  "Wind",
                  "Earth",
                  "Lava",
                  "Fire",
              )
          ),
          Hero(
              id = 3,
              name = "Sakura",
              image = "/images/sakura.jpg",
              about = "Sakura Uchiha is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
              rating = 4.5,
              power = 92,
              month = "Mar",
              day = "28th ",
              family = listOf(
                  "Kizashi",
                  "Mebuki",
                  "Sarada",
                  "Sasuke",
              ),
              abilities = listOf(
                  "Chakra Control",
                  "Medical Ninjutsu",
                  "Strength",
                  "Intelligence"
              ),
              natureTypes = listOf(
                  "Earth",
                  "Water",
                  "Fire",
              )
          ),
      )
  }

    override suspend fun getAllHeroes(page: Int): ApiResponse {
        return ApiResponse(
            success = false
        )
    }

    override suspend fun searchHeroes(name: String): ApiResponse {
        val searchedHeroes = findHeroes(name = name)
        return ApiResponse(
            success = true,
            message = "ok",
            heroes = searchedHeroes
        )
    }

    private fun findHeroes(name: String): List<Hero> {
        val founded = mutableListOf<Hero>()
        return if (name.isNotEmpty()) {
            heroes.forEach { hero ->
                if (hero.name.lowercase().contains(name.lowercase())) {
                    founded.add(hero)
                }
            }
            founded
        } else {
            emptyList()
        }
    }
}