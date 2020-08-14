package com.example.coronastats.data

data class CountryWiseStatsItem(
    val active: String,
    val cases: String,
    val casesPerOneMillion: Double,
    val country: String,
    val countryInfo: CountryInfo,
    val critical: Double,
    val deaths: Double,
    val deathsPerOneMillion: Double,
    val recovered: Double,
    val todayCases: Double,
    val todayDeaths: Double,
    val updated: Double
)