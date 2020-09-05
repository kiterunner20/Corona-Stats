package com.example.coronastats

import com.example.coronastats.data.latestcasesindia.CovidStatsInfo
import com.example.coronastats.local.LocalDatabase
import com.example.coronastats.network.RemoteConfig
import retrofit2.Response
import javax.inject.Inject


open class Repository @Inject constructor(
    private val database: LocalDatabase,
    private val remoteConfig: RemoteConfig
) {

    lateinit var covidStatsInfo: Response<CovidStatsInfo>


    suspend fun getIndiaStats(): Response<CovidStatsInfo> {
        covidStatsInfo = remoteConfig.getIndiaStats()

        database.insert(covidStatsInfo)

        return covidStatsInfo
    }


}