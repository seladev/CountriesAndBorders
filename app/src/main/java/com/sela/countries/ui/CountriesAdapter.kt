package com.sela.countries.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sela.countries.R
import com.sela.countries.data.model.Country
import com.sela.countries.ui.allcountries.OnCountryClickListener

/**
 * CountriesAdapter - Adapter for display all countries
 */
class CountriesAdapter : ListAdapter<Country, CountriesAdapter.CountryViewHolder>(DiffCallback()) {

    var onCountryClickListener: OnCountryClickListener? = null

    class CountryViewHolder(itemView: View ): RecyclerView.ViewHolder(itemView){
        private val nativeName = itemView.findViewById<TextView>(R.id.native_name_text)
        private val englishName = itemView.findViewById<TextView>(R.id.english_name_text)

        fun bind(item: Country, clickListener: OnCountryClickListener?) {
            nativeName.text = item.nativeName
            englishName.text = item.name
            itemView.setOnClickListener { clickListener?.onCountryClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.country_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(getItem(position), onCountryClickListener)
    }

    class DiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.alpha3Code == newItem.alpha3Code
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }
}

