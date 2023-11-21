package com.example.multithreadingpart2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.runBlocking

class BootCompletedReceiver(private val callback: () -> Unit) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            if (runBlocking { getDatastoreNotificationFlag(context) }) {
                callback.invoke()
            }
        }
    }
}