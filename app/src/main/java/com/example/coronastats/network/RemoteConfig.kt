package com.example.coronastats.network

import com.example.coronastats.APIService
import com.example.coronastats.data.CountryWiseStatsItem
import com.example.coronastats.data.latestcasesindia.CovidStatsInfo
import retrofit2.Response
import javax.inject.Inject

class RemoteConfig @Inject constructor(private val apiService: APIService) {


    suspend fun getCountryList(): Response<List<CountryWiseStatsItem>> {
        return apiService.getCoronaStatsOnCountries()
    }

    suspend fun getIndiaStats(): Response<CovidStatsInfo> {
        return apiService.getIndiaStats()
    }

}