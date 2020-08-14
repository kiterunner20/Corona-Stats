package com.example.coronastats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coronastats.data.CountryWiseStatsItem
import com.example.coronastats.databinding.ActivityMainBinding


class CovidMainScreenActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CountryWiseAdapter
    lateinit var countryViewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        countryViewModel = CountryViewModel(application)

        countryViewModel.makeCountryAPICall()

        countryViewModel.getCountryWiseList().observe(this, Observer {
            if (it.isSuccessful) {
                var countryWiseStatsItem = ArrayList<CountryWiseStatsItem>()
                for (country in it.body()!!) {
                    countryWiseStatsItem.add(country)
                }
                setUI(countryWiseStatsItem)
            } else {
                print(it.errorBody().toString())
            }

        })


        setContentView(binding.root)
    }


    private fun setUI(countryWiseStatsItem: ArrayList<CountryWiseStatsItem>) {
        if (countryWiseStatsItem.size > 0) {
            adapter = CountryWiseAdapter(countryWiseStatsItem)
            binding.rcvListResult.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.rcvListResult.adapter = adapter
        }
    }


}