package com.example.coronastats.data.latestcasesindia

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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