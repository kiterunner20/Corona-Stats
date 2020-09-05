package com.example.coronastats.di

import android.content.Context
import androidx.room.Room
import com.example.coronastats.local.LocalDatabase
import com.example.coronastats.local.room.CovidDatabase
import com.example.coronastats.local.room.CovidStatusInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): CovidDatabase {
        return Room.databaseBuilder(
            context,
            CovidDatabase::class.java,
            CovidDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideCovidDao(database: CovidDatabase): CovidStatusInfoDao {
        return database.sleepDao
    }

    @Singleton
    @Provides
    fun provideLocalDatabase(database: CovidDatabase): LocalDatabase {
        return LocalDatabase(database)
    }
}