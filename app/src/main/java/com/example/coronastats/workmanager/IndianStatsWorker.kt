package com.example.coronastats.workmanager

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.coronastats.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class IndianStatsWorker @WorkerInject constructor(
    @Assisted ctx: Context, @Assisted params: WorkerParameters,
    private val repository: Repository
) : Worker(ctx, params) {

    override fun doWork(): Result {

        try {

            CoroutineScope(Dispatchers.IO).launch {
                repository.getIndiaStats()
            }

            return Result.success()

        } catch (e: Exception) {
            return Result.failure()
        }


    }
}