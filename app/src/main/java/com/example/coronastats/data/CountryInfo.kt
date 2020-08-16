package com.example.coronastats.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryInfo(
    val _id: String,
    val country: String,
    val flag: String,
    val iso2: String,
    val iso3: String,
    val lat: Double,
    val long: Double
) :Parcelable