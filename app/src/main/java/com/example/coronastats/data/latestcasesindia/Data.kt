package com.example.coronastats.data.latestcasesindia

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(

    @field:SerializedName("summary")
    val summary: Summary? = null,

    @field:SerializedName("unofficial-summary")
    val unofficialSummary: List<UnofficialSummaryItem?>? = null,

    @field:SerializedName("regional")
    val regional: List<RegionalItem?>? = null
) : Parcelable
