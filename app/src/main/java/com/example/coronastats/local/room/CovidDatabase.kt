package com.example.coronastats.local.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CovidStatusInfo::class], version = 2, exportSchema = false)
abstract class CovidDatabase : RoomDatabase() {

    abstract val sleepDao: CovidStatusInfoDao

    companion object {
        @Volatile
        private var INSTANCE: CovidDatabase? = null

        fun getInstance(context: Context?): CovidDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    Log.v("Thread", Thread.currentThread().name)
                    instance = Room.databaseBuilder(
                        context!!.applicationContext,
                        CovidDatabase::class.java,
                        "sleep_history_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}