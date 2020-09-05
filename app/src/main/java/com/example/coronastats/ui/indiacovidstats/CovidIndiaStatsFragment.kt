package com.example.coronastats.ui.indiacovidstats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coronastats.CovidMainScreenActivity
import com.example.coronastats.databinding.FragmentCovidIndiaStatsBinding
import com.example.coronastats.ui.BaseFragment
import com.example.coronastats.ui.viewstate.CountryViewState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CovidIndiaStatsFragment : BaseFragment() {

    private val indiaStatsViewModel: IndiaStatsViewModel by viewModels()
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

        indiaStatsViewModel.callCovidStatsAPI(context)
        observerChangesFromVM()

        return binding.root
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun observerChangesFromVM() {
        indiaStatsViewModel.getIndiaCovidStats().observe(activity as CovidMainScreenActivity,
            Observer {
                when (it) {
                    CountryViewState.PROGRESS -> showProgress()
                    CountryViewState.FAILED -> showError(it.error)
                    CountryViewState.SUCCESS -> {
                        showSuccess()
                        adapter = IndiaCovidStatusAdapter()
                        binding.rcvIndiaData.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        adapter.setData(it.data?.body())
                        binding.rcvIndiaData.adapter = adapter
                    }
                }
            })
    }

    fun showProgress() {
        binding.progressbar.visibility = View.VISIBLE
        binding.error.visibility = View.GONE
    }

    fun showError(error: String?) {
        binding.progressbar.visibility = View.GONE
        binding.error.text = error ?: "Failed to fetch fata"
        binding.rcvIndiaData.visibility = View.GONE
    }

    fun showSuccess() {
        binding.progressbar.visibility = View.GONE
        binding.error.visibility = View.GONE
    }
}