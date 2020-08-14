package com.example.coronastats.data

data class CountryInfo(
    val _id: String,
    val country: String,
    val flag: String,
    val iso2: String,
    val iso3: String,
    val lat: Double,
    val long: Double
)