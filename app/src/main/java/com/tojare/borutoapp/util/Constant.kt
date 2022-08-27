package com.tojare.borutoapp.util

import com.tojare.borutoapp.domain.model.Hero

object Constant {

    const val BASE_URL = "http://192.168.55.16:8080/"
    //const val BASE_URL = "http://10.0.2.2:8080/"

    const val BORUTO_DATABASE = "boruto_database"
    const val DETAILS_ARGUMENT_KEY = "heroId"
    const val HERO_DATABASE_TABLE = "hero_table"
    const val HERO_REMOTE_KEYS_DATABASE_TABLE = "hero_remote_key_table"

    const val ON_BOARDING_PAGE_COUNT = 3
    const val LAST_ON_BOARDING_PAGE = 2

    const val PREFERENCES_NAME = "boruto_preferences"
    const val PREFERENCES_KEY = "on_boarding_completed"


     val SAMPLE_HERO = Hero(
        id = 14,
        name = "Sarada",
        image = "/images/sarada.jpg",
        about = "Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
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
    )
}