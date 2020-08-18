package com.example.coronastats

import android.content.Context
import com.example.coronastats.data.CountryWiseStatsItem
import com.example.coronastats.data.latestcasesindia.CovidStatsInfo
import com.example.coronastats.local.LocalDatabase
import com.example.coronastats.network.RemoteConfig
import retrofit2.Response


class Repository {

    val remoteConfig: RemoteConfig = RemoteConfig()
    val localDatabase: LocalDatabase = LocalDatabase()


    lateinit var covidStatsInfo: Response<CovidStatsInfo>

    suspend fun getCountryList(): Response<List<CountryWiseStatsItem>> {
        return remoteConfig.getCountryList()
    }

    suspend fun getIndiaStats(context: Context?): Response<CovidStatsInfo> {
        covidStatsInfo = remoteConfig.getIndiaStats()

        localDatabase.insert(covidStatsInfo, context)

        return covidStatsInfo
    }


}