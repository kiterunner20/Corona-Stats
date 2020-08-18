package com.example.coronastats.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface CovidStatusInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(covidStatusInfo: CovidStatusInfo?)


}