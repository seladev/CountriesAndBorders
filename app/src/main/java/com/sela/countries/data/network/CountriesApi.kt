package com.sela.countries.data.network

import com.sela.countries.data.model.Country
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Countries Api - for get all countries data from network
 */
interface CountriesApi {

    /**
     * get all countries
     */
    @GET("all")
    suspend fun allCountries(): List<Country>

}