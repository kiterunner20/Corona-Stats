package com.example.coronastats.workmanager

import android.content.Context
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class ManageWorkManager(ctx: Context) {

    private val workManager = WorkManager.getInstance(ctx)

    fun doSomeBackGroundWork() {

        workManager.enqueue(OneTimeWorkRequest.from(IndianStatsWorker::class.java))
    }


}