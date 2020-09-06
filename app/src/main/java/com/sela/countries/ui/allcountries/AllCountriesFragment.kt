package com.sela.countries.ui.allcountries

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.sela.countries.R
import com.sela.countries.data.model.Country
import com.sela.countries.ui.BaseFragment
import com.sela.countries.ui.CountriesAdapter
import com.sela.countries.ui.country.CountryFragment
import com.sela.countries.ui.country.CountryViewModel
import com.sela.countries.ui.country.CountryViewModelFactory
import com.sela.countries.utils.logDebug
import kotlinx.android.synthetic.main.fragment_allcountries.*

/**
 * AllCountriesFragment - All Countries Screen
 */
class AllCountriesFragment : BaseFragment() {

    override var resourceLayout = R.layout.fragment_allcountries
    private lateinit var viewModelFactory: AllCountriesViewModelFactory
    private lateinit var viewModel: AllCountriesViewModel
    private lateinit var countriesAdapter: CountriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFactory = AllCountriesViewModelFactory(activity?.application!!)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AllCountriesViewModel::class.java)


        initViews()
        initObservers()
    }

    override fun initViews() {
        countriesAdapter = CountriesAdapter()
        countriesAdapter.onCountryClickListener = object : OnCountryClickListener {
            override fun onCountryClick(country: Country) {
                this@AllCountriesFragment.logDebug("click on $country")

                val bundle = bundleOf(CountryFragment.COUNTRY to  Gson().toJson(country))
                view?.findNavController()?.navigate(R.id.action_all_countries_fragment_to_country_fragment, bundle)
            }
        }


        countries_recycler_view.apply {
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            this.addItemDecoration(dividerItemDecoration)
            this.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            this.adapter = countriesAdapter
        }


    }

    override fun initObservers() {

        viewModel.countryList.observe(viewLifecycleOwner, Observer {
            countriesAdapter.submitList(it)
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            loading_indicator.apply {
                if(it) this.show() else this.hide()
            }
        })
    }


}