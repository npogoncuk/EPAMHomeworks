package com.example.multithreadingpart2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.multithreadingpart2.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


private const val CHANNEL_NAME = "channel_name"
const val CHANNEL_ID = "channel_id"
private const val CHANNEL_DESCRIPTION = "This channel sends the same notification every minute"


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "notifications")
val DATASTORE_NOTIFICATION_KEY = booleanPreferencesKey("notifications_key")

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var broadcastReceiver: BootCompletedReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()

        binding.buttonStart.setOnClickListener {
            start()
            changeDatastoreNotificationFlag(true)
            //it.isEnabled = false
        }
        binding.buttonStop.setOnClickListener {
            stop()
            changeDatastoreNotificationFlag(false)
        }

    }

    private fun start() {
        val workRequest =
            PeriodicWorkRequestBuilder<ShowNotificationWorker>(15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }

    private fun stop() {
        WorkManager.getInstance(applicationContext).cancelAllWork()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = CHANNEL_DESCRIPTION
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun changeDatastoreNotificationFlag(flag: Boolean) {
        lifecycleScope.launch {
            dataStore.edit { notifications ->
                //val currentValue = notifications[DATASTORE_NOTIFICATION_KEY] ?: false
                notifications[DATASTORE_NOTIFICATION_KEY] = flag
            }
        }
    }


    override fun onStart() {
        super.onStart()
        if (broadcastReceiver == null) broadcastReceiver = BootCompletedReceiver {
            binding.buttonStart.callOnClick()
        }
        registerReceiver(broadcastReceiver, IntentFilter(Intent.ACTION_BOOT_COMPLETED))
    }

    override fun onStop() {
        unregisterReceiver(broadcastReceiver)
        super.onStop()
    }
}
suspend fun getDatastoreNotificationFlag(context: Context): Boolean {
    val preferences = context.dataStore.data.first()
    return preferences[DATASTORE_NOTIFICATION_KEY] ?: false
}