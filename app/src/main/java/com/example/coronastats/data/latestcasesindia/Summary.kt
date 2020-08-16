package com.example.coronastats.data.latestcasesindia

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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
    val deaths: String? = null,

    val totalcase: String = confirmedCasesIndian + confirmedCasesForeign
) : Parcelable