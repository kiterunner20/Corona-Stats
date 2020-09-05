package com.example.coronastats.local

import com.example.coronastats.data.latestcasesindia.CovidStatsInfo
import com.example.coronastats.local.room.CovidDatabase
import com.example.coronastats.local.room.CovidStatusInfo
import retrofit2.Response
import javax.inject.Inject

class LocalDatabase @Inject constructor(private val database: CovidDatabase) {

    suspend fun insert(covidStatsInfo: Response<CovidStatsInfo>) {
        if (covidStatsInfo.isSuccessful) {
            val covidInfo = CovidStatusInfo(
                System.currentTimeMillis(), covidStatsInfo.body()?.lastRefreshed,
                covidStatsInfo.body()?.lastRefreshed, covidStatsInfo.body()?.data?.summary
            )

            database.sleepDao.insert(covidInfo)
        }
    }
}