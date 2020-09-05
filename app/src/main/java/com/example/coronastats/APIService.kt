package com.example.coronastats

import com.example.coronastats.data.latestcasesindia.CovidStatsInfo
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("stats/latest")
    suspend fun getIndiaStats(): Response<CovidStatsInfo>

}