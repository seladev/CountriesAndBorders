package com.sela.countries.ui.country

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.sela.countries.R
import com.sela.countries.data.model.Country
import com.sela.countries.ui.BaseFragment
import com.sela.countries.ui.CountriesAdapter
import com.sela.countries.utils.setVisible
import kotlinx.android.synthetic.main.fragment_allcountries.*

/**
 * Country screen - display list of all border countries
 */
class CountryFragment : BaseFragment() {

    override var resourceLayout = R.layout.fragment_allcountries
    private lateinit var viewModelFactory: CountryViewModelFactory
    private lateinit var viewModel: CountryViewModel

    private lateinit var countriesAdapter: CountriesAdapter

    companion object{
        const val COUNTRY = "country"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val country= Gson().fromJson<Country>(arguments?.getString(COUNTRY), Country::class.java)

        viewModelFactory = CountryViewModelFactory(country, activity?.application!!)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CountryViewModel::class.java)

        initViews()
        initObservers()
    }


    override fun initViews() {

        countriesAdapter = CountriesAdapter()
        countries_recycler_view.apply {
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            this.addItemDecoration(dividerItemDecoration)
            this.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            this.adapter = countriesAdapter
        }
    }

    override fun initObservers() {
        viewModel.countryName.observe(viewLifecycleOwner, Observer {
            activity?.title = it
        })

        viewModel.borderList.observe(viewLifecycleOwner, Observer {
            countriesAdapter.submitList(it)
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            loading_indicator.apply {
                if(it) this.show() else this.hide()
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            error_text.setVisible(it.first)
            error_text.setText(it.second)
        })
    }

}