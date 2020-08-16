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

    val type: Int = 1
) : Parcelable
