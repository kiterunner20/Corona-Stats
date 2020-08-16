package com.example.coronastats.ui.indiacovidstats

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coronastats.CovidMainScreenActivity
import com.example.coronastats.databinding.FragmentCovidIndiaStatsBinding
import com.example.coronastats.ui.BaseFragment

class CovidIndiaStatsFragment : BaseFragment() {

    private lateinit var indiaStatsViewModel: IndiaStatsViewModel
    private lateinit var binding: FragmentCovidIndiaStatsBinding
    private lateinit var navController: NavController
    private lateinit var adapter: IndiaCovidStatusAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCovidIndiaStatsBinding.inflate(inflater, container, false)

        navController = (activity as CovidMainScreenActivity).getNavController()


        indiaStatsViewModel =
            IndiaStatsViewModel(application = Application())



        indiaStatsViewModel.callCovidStatsAPI()
        indiaStatsViewModel.getIndiaCovidStats().observe(activity as CovidMainScreenActivity,
            Observer {
                if (it.isSuccessful) {
                    Log.v("Success", "Called successfully")
                    adapter = IndiaCovidStatusAdapter()
                    binding!!.rcvIndiaData.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter.setData(it.body())
                    binding!!.rcvIndiaData.adapter = adapter
                }
            })

        return binding.root
    }

}