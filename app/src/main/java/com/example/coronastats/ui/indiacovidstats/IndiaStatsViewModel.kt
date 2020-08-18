package com.example.coronastats.ui.indiacovidstats

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coronastats.Repository
import com.example.coronastats.data.latestcasesindia.CovidStatsInfo
import com.example.coronastats.ui.viewstate.CountryViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
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

    fun callCovidStatsAPI(context: Context?) {
        showProgress()

        viewModelScope.launch {
            try {
                responseForCovidStats = repository.getIndiaStats(context)
                Log.v("Thread", Thread.currentThread().name)
                withData(responseForCovidStats)
            } catch (e: Exception) {
                onFailiure(e.message.toString())
            }

        }
    }


    private suspend fun withData(responseForCovidStats: Response<CovidStatsInfo>) {
        withContext(Main) {
            if (responseForCovidStats.isSuccessful) {
                Log.v("Thread2", Thread.currentThread().name)
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


}