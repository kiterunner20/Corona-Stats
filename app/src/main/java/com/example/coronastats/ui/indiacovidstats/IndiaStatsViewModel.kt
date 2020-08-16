package com.example.coronastats.ui.indiacovidstats

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coronastats.Repository
import com.example.coronastats.data.latestcasesindia.CovidStatsInfo
import com.example.coronastats.ui.viewstate.CountryViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class IndiaStatsViewModel : ViewModel() {

    private var indiaCovidStats: MutableLiveData<CountryViewState> = MutableLiveData()
    private lateinit var responseForCovidStats: Response<CovidStatsInfo>

    val repository: Repository =
        Repository()

    fun getIndiaCovidStats(): MutableLiveData<CountryViewState> {
        return indiaCovidStats
    }

    fun callCovidStatsAPI() {
        showProgress()
        CoroutineScope(Dispatchers.IO).launch {
            responseForCovidStats = repository.getIndiaStats()
            withData(responseForCovidStats)
        }
    }


    private suspend fun withData(responseForCovidStats: Response<CovidStatsInfo>) {
        withContext(Main) {
            if (responseForCovidStats.isSuccessful) {
                CountryViewState.SUCCESS.data = responseForCovidStats
                indiaCovidStats.postValue(CountryViewState.SUCCESS)
            } else {
                onFailiure(responseForCovidStats.errorBody().toString())
            }
        }
    }

    private fun showProgress() {
        indiaCovidStats.postValue(CountryViewState.PROGRESS)
    }

    private fun onFailiure(error: String) {
        CountryViewState.FAILED.error = error
        indiaCovidStats.postValue(CountryViewState.FAILED)
    }

    override fun onCleared() {
        super.onCleared()
    }

}