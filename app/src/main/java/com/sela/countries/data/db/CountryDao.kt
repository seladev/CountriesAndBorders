package com.sela.countries.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sela.countries.data.model.Country

/**
 * CountryDao interface for Room db
 */
@Dao
interface CountryDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(countries:List<Country>)

    @Query("SELECT * FROM DatabaseCountry ")
    fun getAllCountries(): LiveData<List<Country>>

    @Query("SELECT * FROM DatabaseCountry WHERE alpha3Code IN(:borders)")
    fun getCountriesByBorders(borders: List<String>): List<Country>
}