package com.sela.countries.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.sela.countries.data.db.CountriesDatabase
import com.sela.countries.data.model.Country
import com.sela.countries.data.network.CountriesApi
import com.sela.countries.data.network.CountriesRetrofitService
import com.sela.countries.data.network.CountriesRetrofitService.countriesApi
import com.sela.countries.utils.logDebug
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Country Repository for all the comunication with the net and the db
 */
class CountryRepository(private val database:CountriesDatabase) {

    val countries: LiveData<List<Country>> = database.countryDao.getAllCountries()

    val borders = MutableLiveData<List<Country>>()

    suspend fun getCountriesByBorders(codes:List<String>) {
        withContext(Dispatchers.IO) {
            try {
                val bordersCountries = database.countryDao.getCountriesByBorders(codes)
                borders.postValue(bordersCountries)
                logDebug("bordersCountries = $bordersCountries")
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }


    suspend fun refreshCountries(){
        withContext(Dispatchers.IO) {
            try {
                val allCountries = countriesApi.allCountries()
                logDebug("All countries from network = $allCountries")
                database.countryDao.insertAll(allCountries)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

}