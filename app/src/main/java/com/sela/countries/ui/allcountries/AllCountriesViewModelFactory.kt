package com.sela.countries.ui.allcountries

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory for constructing AllCountriesViewModel with parameter
 */

class AllCountriesViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllCountriesViewModel::class.java)) {
            return AllCountriesViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}
