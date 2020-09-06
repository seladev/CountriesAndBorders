package com.sela.countries.ui.allcountries

import com.sela.countries.data.model.Country

/**
 * OnCountryClickListener - listener when user click on country item
 */
interface OnCountryClickListener {
    fun onCountryClick(country: Country)
}