package com.example.coronastats.local.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.coronastats.data.latestcasesindia.Summary

@Entity(tableName = "covid_status_info")
data class CovidStatusInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "last_refreshed")
    val lastRefreshed: String? = null,

    @ColumnInfo(name = "last_orgin_update")
    val lastOrginUpdate: String? = null,


    @Embedded
    val data: Summary?
)