package com.sela.countries.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sela.countries.utils.logError

/**
 * Converters for Room db
 */


object Converters {

    val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun stringToList(data: String?): List<String> {
        return  if(data == null)  {
            emptyList()
        } else {
            return gson.fromJson(data, object : TypeToken<List<String>>() {}.type)
        }
    }

    @TypeConverter
    @JvmStatic
    fun listToString(data: List<String>?):String {
        return gson.toJson(data)
    }
}
