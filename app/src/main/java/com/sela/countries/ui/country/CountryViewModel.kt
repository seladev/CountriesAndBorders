package com.sela.countries.ui.country

import android.app.Application
import androidx.lifecycle.*
import com.sela.countries.data.db.getDatabase
import com.sela.countries.data.model.Country
import com.sela.countries.data.repository.CountryRepository
import com.sela.countries.utils.logDebug
import com.sela.countries.utils.logError
import kotlinx.coroutines.*
import java.io.IOException

/**
 * Created by seladev
 */
class CountryViewModel(private val country: Country, application: Application) : ViewModel(){

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val countryRepository = CountryRepository(getDatabase(application))

    private var _countryName = MutableLiveData<String>()
    val countryName : LiveData<String>
        get() = _countryName

    private var _error = MutableLiveData<Pair<Boolean, String>>()
    val error : LiveData<Pair<Boolean, String>>
        get() = _error

    private var _loading = MutableLiveData<Boolean>()
    val loading = _loading


    var borderList = Transformations.map(countryRepository.borders) {
        _error.value = (it.isEmpty() to (if(it.isEmpty()) "No Borders" else ""))
        return@map it
    }


    init {
        logDebug("$country")
        _countryName.value = country.name
        _loading.value = false
        loadBorders()
    }

    private fun loadBorders() {
        viewModelScope.launch {
            try {
                countryRepository.getCountriesByBorders(country.borders)
            } catch (e: IOException) {
                logError("Error while refreshCountries ${e.localizedMessage}")
                e.printStackTrace()
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}