package com.example.coronastats

import android.app.Application
import com.example.coronastats.workmanager.ManageWorkManager

class CoronaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val manageWorkManager = ManageWorkManager(applicationContext)
        manageWorkManager.doSomeBackGroundWork()
    }

}