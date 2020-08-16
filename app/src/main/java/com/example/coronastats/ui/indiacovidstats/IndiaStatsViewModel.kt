package com.example.coronastats.ui.indiacovidstats

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.coronastats.Repository
import com.example.coronastats.data.latestcasesindia.CovidStatsInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class IndiaStatsViewModel(application: Application) : AndroidViewModel(application) {

    private var indiaCovidStats: MutableLiveData<Response<CovidStatsInfo>> = MutableLiveData()
    private lateinit var responseForCovidStats: Response<CovidStatsInfo>

    val repository: Repository =
        Repository()

    fun getIndiaCovidStats(): MutableLiveData<Response<CovidStatsInfo>> {
        return indiaCovidStats
    }

    fun callCovidStatsAPI() {
        CoroutineScope(Dispatchers.Default).launch {
            responseForCovidStats = repository.getIndiaStats()
            withData(responseForCovidStats)
        }
    }

    private suspend fun withData(responseForCovidStats: Response<CovidStatsInfo>) {
        withContext(Main) {
            indiaCovidStats.postValue(responseForCovidStats)
        }
    }

}