package com.example.coronastats.data.latestcasesindia

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CovidStatsInfo(

    @field:SerializedName("lastRefreshed")
    val lastRefreshed: String? = null,

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("lastOriginUpdate")
    val lastOriginUpdate: String? = null,

    val type :Int = 1
) : Parcelable

@Parcelize
data class Data(

    @field:SerializedName("summary")
    val summary: Summary? = null,

    @field:SerializedName("unofficial-summary")
    val unofficialSummary: List<UnofficialSummaryItem?>? = null,

    @field:SerializedName("regional")
    val regional: List<RegionalItem?>? = null
) : Parcelable

@Parcelize
data class Summary(

    @field:SerializedName("total")
    val total: String? = null,

    @field:SerializedName("confirmedButLocationUnidentified")
    val confirmedButLocationUnidentified: Long? = null,

    @field:SerializedName("confirmedCasesForeign")
    val confirmedCasesForeign: Long? = null,

    @field:SerializedName("discharged")
    val discharged: String? = null,

    @field:SerializedName("confirmedCasesIndian")
    val confirmedCasesIndian: String? = null,

    @field:SerializedName("deaths")
    val deaths: String? = null
) : Parcelable

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

@Parcelize
data class UnofficialSummaryItem(

    @field:SerializedName("total")
    val total: String? = null,

    @field:SerializedName("recovered")
    val recovered: String? = null,

    @field:SerializedName("active")
    val active: String? = null,

    @field:SerializedName("source")
    val source: String? = null,

    @field:SerializedName("deaths")
    val deaths: String? = null
) : Parcelable
