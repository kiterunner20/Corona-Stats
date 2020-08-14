package com.example.coronastats

import com.example.coronastats.data.CountryWiseStatsItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("countries")
    suspend fun getCoronaStatsOnCountries(): Response<List<CountryWiseStatsItem>>

}