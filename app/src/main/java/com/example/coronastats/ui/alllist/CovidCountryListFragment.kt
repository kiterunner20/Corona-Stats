package com.example.coronastats.ui.alllist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coronastats.CovidMainScreenActivity
import com.example.coronastats.R
import com.example.coronastats.data.CountryWiseStatsItem
import com.example.coronastats.databinding.FragmentCountryListBinding
import com.example.coronastats.ui.BaseFragment

class CovidCountryListFragment : BaseFragment(), CountryWiseAdapter.CountryClicked {


    private lateinit var binding: FragmentCountryListBinding
    private lateinit var adapter: CountryWiseAdapter
    private lateinit var countryViewModel: CountryViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryListBinding.inflate(inflater, container, false)

        navController = (activity as CovidMainScreenActivity).getNavController()
        countryViewModel = ViewModelProvider(requireActivity()).get(CountryViewModel::class.java)


        countryViewModel.makeCountryAPICall()

        binding.btnIndianData.setOnClickListener {
            navController.navigate(R.id.indian_covid_stats)
        }

        observeChangesForVM()

        return binding.root
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeChangesForVM() {
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
    }

    private fun setUI(countryWiseStatsItem: ArrayList<CountryWiseStatsItem>) {
        if (countryWiseStatsItem.size > 0) {
            adapter = CountryWiseAdapter(
                this,
                countryWiseStatsItem
            )
            binding.rcvListResult.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rcvListResult.adapter = adapter

        }
    }

    override fun countryItemClicked(item: CountryWiseStatsItem) {

        val countryInfo = item.country
        val action =
            CovidCountryListFragmentDirections.actionToMoreInfo(
                countryInfo
            )
        navController.navigate(action)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.v("CovidCountry", "Called")
    }

    override fun onDetach() {
        super.onDetach()
        Log.v("CovidCountry", "Called onDetach")
    }
}