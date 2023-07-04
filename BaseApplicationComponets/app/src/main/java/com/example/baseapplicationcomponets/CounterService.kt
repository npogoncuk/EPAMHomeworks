package com.example.baseapplicationcomponets

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class CounterService : Service() {

    private val binder = CounterServiceBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    private var counter = 0

    fun howManyTimeWasAccessed() = ++counter

    inner class CounterServiceBinder : Binder() {
        fun getService() = this@CounterService
    }
}