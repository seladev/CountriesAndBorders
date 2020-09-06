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

    private var _loading = MutableLiveData<Boolean>()
    val loading = MediatorLiveData<Boolean>()

    private var _error = MutableLiveData<Pair<Boolean, String>>()
    val error : LiveData<Pair<Boolean, String>>
        get() = _error


    var borderList = Transformations.map(countryRepository.borders) {
        _error.value = (it.isEmpty() to (if(it.isEmpty()) "No Borders" else ""))
        return@map it
    }


    init {
        logDebug("$country" )
        _countryName.value = country.name
        createLoadingLiveData()
        loadBorders()
    }

    private fun createLoadingLiveData() {
        loading.addSource(_loading) {
            loading.value = it
        }

        loading.addSource(borderList) {
            loading.value = it.isEmpty()
        }
    }

    private fun loadBorders() {
        viewModelScope.launch {
            try {
                _loading.value = true
                countryRepository.getCountriesByBorders(country.borders)
            } catch (e: IOException) {
                logError("Error while refreshCountries ${e.localizedMessage}")
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}