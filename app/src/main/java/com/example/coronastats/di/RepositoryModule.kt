package com.example.coronastats.di

import com.example.coronastats.Repository
import com.example.coronastats.local.LocalDatabase
import com.example.coronastats.network.RemoteConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(database: LocalDatabase, remoteServer: RemoteConfig): Repository {
        return Repository(database, remoteServer)
    }

}