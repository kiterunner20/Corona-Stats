package com.example.coronastats

import com.example.coronastats.data.CountryWiseStatsItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://disease.sh/v3/covid-19/"


class Repository {

    private val apiRequest = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIService::class.java)


    suspend fun getCountryList(): Response<List<CountryWiseStatsItem>> {
        return apiRequest.getCoronaStatsOnCountries()
    }


}