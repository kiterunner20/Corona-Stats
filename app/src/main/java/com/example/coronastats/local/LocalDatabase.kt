package com.example.coronastats.local

import android.content.Context
import com.example.coronastats.data.latestcasesindia.CovidStatsInfo
import com.example.coronastats.local.room.CovidDatabase
import com.example.coronastats.local.room.CovidStatusInfo
import retrofit2.Response

class LocalDatabase {

    suspend fun insert(covidStatsInfo: Response<CovidStatsInfo>, context: Context?) {
        if (covidStatsInfo.isSuccessful) {
            val database = CovidDatabase.getInstance(context)

            val covidInfo = CovidStatusInfo(
                System.currentTimeMillis(), covidStatsInfo.body()?.lastRefreshed,
                covidStatsInfo.body()?.lastRefreshed, covidStatsInfo.body()?.data?.summary
            )

            database.sleepDao.insert(covidInfo)
        }
    }
}