package com.example.coronastats

import android.content.Context
import android.util.Log
import com.example.coronastats.data.CountryWiseStatsItem
import com.example.coronastats.data.latestcasesindia.CovidStatsInfo
import com.example.coronastats.data.room.CovidDatabase
import com.example.coronastats.data.room.CovidStatusInfo
import retrofit2.Response


class Repository {

    val apiRequest = RetrofiServiceBuilder.buildService(APIService::class.java)
    val indianDataRequest = RetrofiServiceBuilder.buildServiceIndia(APIService::class.java)

    lateinit var covidStatsInfo: Response<CovidStatsInfo>

    suspend fun getCountryList(): Response<List<CountryWiseStatsItem>> {
        return apiRequest.getCoronaStatsOnCountries()
    }

    suspend fun getIndiaStats(context: Context?): Response<CovidStatsInfo> {
        covidStatsInfo = indianDataRequest.getIndiaStats()

        if (covidStatsInfo.isSuccessful) {
            val database = CovidDatabase.getInstance(context)

            val covidInfo = CovidStatusInfo(
                System.currentTimeMillis(), covidStatsInfo.body()?.lastRefreshed,
                covidStatsInfo.body()?.lastRefreshed, covidStatsInfo.body()?.data?.summary
            )

            database.sleepDao.insert(covidInfo)
        }

        return indianDataRequest.getIndiaStats()
    }


}