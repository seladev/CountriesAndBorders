package com.sela.countries.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sela.countries.data.model.Country

/**
 * CountriesDatabase by Room
 */

@Database(entities = [Country::class], version = 1)
@TypeConverters(Converters::class)
abstract class CountriesDatabase: RoomDatabase() {
    abstract val countryDao: CountryDao
}

private lateinit var INSTANCE: CountriesDatabase


fun getDatabase(context: Context): CountriesDatabase {
    synchronized(CountriesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                CountriesDatabase::class.java,
                "countries").build()
        }
    }
    return INSTANCE
}