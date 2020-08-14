package com.example.coronastats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coronastats.data.CountryWiseStatsItem
import com.example.coronastats.databinding.ItemCountryWiseListBinding

class CountryWiseAdapter(private val list: ArrayList<CountryWiseStatsItem>) :
    RecyclerView.Adapter<CountryWiseAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryWiseAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountryWiseListBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val countryData = list.get(position)
        holder.bind(countryData)
    }


    inner class ViewHolder(val binding: ItemCountryWiseListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(countryData: CountryWiseStatsItem) {
            binding.tvCountrydata.text = countryData.country
            binding.tvTotalCases.text = countryData.cases
            binding.tvActive.text = countryData.active
            binding.tvDeaths.text = countryData.deaths.toString()
            binding.tvRecovered.text = countryData.recovered.toString()
        }


    }


}