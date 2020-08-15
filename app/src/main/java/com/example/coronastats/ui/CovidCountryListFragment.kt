package com.example.coronastats.ui

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coronastats.CountryViewModel
import com.example.coronastats.CountryWiseAdapter
import com.example.coronastats.CovidMainScreenActivity
import com.example.coronastats.data.CountryWiseStatsItem
import com.example.coronastats.databinding.FragmentCountryListBinding

class CovidCountryListFragment : BaseFragment(), CountryWiseAdapter.CountryClicked {


    private lateinit var binding: FragmentCountryListBinding
    private lateinit var adapter: CountryWiseAdapter
    lateinit var countryViewModel: CountryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryListBinding.inflate(inflater, container, false)




        countryViewModel = CountryViewModel(application = Application())

        countryViewModel.makeCountryAPICall()

        countryViewModel.getCountryWiseList()
            .observe(activity as CovidMainScreenActivity, Observer {
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


        return binding!!.root
    }


    private fun setUI(countryWiseStatsItem: ArrayList<CountryWiseStatsItem>) {
        if (countryWiseStatsItem.size > 0) {
            adapter = CountryWiseAdapter(this, countryWiseStatsItem)
            binding!!.rcvListResult.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding!!.rcvListResult.adapter = adapter

        }
    }

    override fun countryItemClicked(item: CountryWiseStatsItem) {

        val countryInfo = item.country
        val action = CovidCountryListFragmentDirections.actionToMoreInfo(countryInfo)
        findNavController().navigate(action)

    }

}