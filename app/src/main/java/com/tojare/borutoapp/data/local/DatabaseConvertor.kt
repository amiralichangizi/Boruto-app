package com.tojare.borutoapp.data.local

import androidx.room.TypeConverter

class DatabaseConvertor {

    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        val stringBuilder = StringBuilder()
        list.forEachIndexed { index, item ->
            stringBuilder.append(item)
            if (index != list.size - 1) {
                stringBuilder.append(separator)
            }
        }
        return stringBuilder.toString()
    }

    @TypeConverter
    fun convertStringToList(string: String): List<String> = string.split(separator)
}