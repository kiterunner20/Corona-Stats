package com.example.coronastats.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CovidStatusInfo::class], version = 2, exportSchema = false)
abstract class CovidDatabase : RoomDatabase() {

    abstract val sleepDao: CovidStatusInfoDao

    companion object {
        val DATABASE_NAME: String = "covid_database_db"
    }

}