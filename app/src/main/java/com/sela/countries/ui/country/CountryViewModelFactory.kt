package com.sela.countries.ui.country

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sela.countries.data.model.Country

/**
 * Factory for constructing CountryViewModel with parameter
 */
class CountryViewModelFactory(private val country: Country, val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java) ) {
            return CountryViewModel(country, app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

