package com.example.coronastats

import com.example.coronastats.data.CountryWiseStatsItem
import com.example.coronastats.data.latestcasesindia.CovidStatsInfo
import retrofit2.Response


class Repository {

    val apiRequest = RetrofiServiceBuilder.buildService(APIService::class.java)
    val indianDataRequest = RetrofiServiceBuilder.buildServiceIndia(APIService::class.java)

    suspend fun getCountryList(): Response<List<CountryWiseStatsItem>> {
        return apiRequest.getCoronaStatsOnCountries()
    }

    suspend fun getIndiaStats(): Response<CovidStatsInfo> {
        return indianDataRequest.getIndiaStats()
    }


}