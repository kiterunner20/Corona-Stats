package com.example.coronastats.data.latestcasesindia

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegionalItem(

    @field:SerializedName("loc")
    val loc: String? = null,

    @field:SerializedName("confirmedCasesForeign")
    val confirmedCasesForeign: String? = null,

    @field:SerializedName("discharged")
    val discharged: String? = null,

    @field:SerializedName("confirmedCasesIndian")
    val confirmedCasesIndian: String? = null,

    @field:SerializedName("deaths")
    val deaths: String? = null,

    @field:SerializedName("totalConfirmed")
    val totalConfirmed: String? = null
) : Parcelable