package com.example.coronastats.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.coronastats.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class IndianStatsWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    val repository: Repository = Repository()


    override fun doWork(): Result {

        try {

            CoroutineScope(Dispatchers.IO).launch {
                repository.getIndiaStats(applicationContext)
            }

            return Result.success()

        } catch (e: Exception) {
            return Result.failure()
        }


    }
}