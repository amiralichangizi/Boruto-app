package com.tojare.borutoapp.util

import com.tojare.borutoapp.domain.model.Hero

object Constant {

    //const val BASE_URL = "http://192.168.55.16:8080/"
    const val BASE_URL = "http://10.0.2.2:8080/"

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
        about = "Sarada Uzumaki is a shinobi of Konohagakure's Uzamali ....",
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