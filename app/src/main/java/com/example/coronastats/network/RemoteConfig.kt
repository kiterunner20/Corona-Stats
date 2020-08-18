package com.example.coronastats.network

import com.example.coronastats.APIService
import com.example.coronastats.RetrofiServiceBuilder
import com.example.coronastats.data.CountryWiseStatsItem
import com.example.coronastats.data.latestcasesindia.CovidStatsInfo
import retrofit2.Response

class RemoteConfig {

    val apiRequest = RetrofiServiceBuilder.buildService(APIService::class.java)
    val indianDataRequest = RetrofiServiceBuilder.buildServiceIndia(APIService::class.java)

    suspend fun getCountryList(): Response<List<CountryWiseStatsItem>> {
        return apiRequest.getCoronaStatsOnCountries()
    }

    suspend fun getIndiaStats(): Response<CovidStatsInfo> {
        return indianDataRequest.getIndiaStats()
    }

}