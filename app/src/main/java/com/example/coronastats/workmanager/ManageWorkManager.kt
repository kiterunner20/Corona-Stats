package com.example.coronastats.workmanager

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class ManageWorkManager(ctx: Context) {

    private val workManager = WorkManager.getInstance(ctx)

    fun doSomeBackGroundWork() {

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()
        val work = PeriodicWorkRequestBuilder<IndianStatsWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()
        workManager.enqueueUniquePeriodicWork(
            "My periodic work",
            ExistingPeriodicWorkPolicy.REPLACE,
            work
        )
    }


}