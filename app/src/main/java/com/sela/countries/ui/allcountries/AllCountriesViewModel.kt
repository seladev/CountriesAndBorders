package com.sela.countries.ui.allcountries

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
 * AllCountriesViewModel - the view model for all AllCountries screen
 */
class AllCountriesViewModel(application: Application) : AndroidViewModel(application){

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val countryRepository = CountryRepository(getDatabase(application))

    var countryList = countryRepository.countries

    private var _loading = MutableLiveData<Boolean>()

    val loading = MediatorLiveData<Boolean>()

    init {
        createLoadingLiveData()

        refreshDataFromRepository()
    }

    private fun createLoadingLiveData() {
        loading.addSource(_loading) {
            loading.value = it
        }

        loading.addSource(countryList) {
            loading.value = it.isEmpty()
        }
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                _loading.value = true
                countryRepository.refreshCountries()
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