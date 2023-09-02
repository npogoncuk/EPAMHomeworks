package com.example.multithreadingpart2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.multithreadingpart2.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener { start() }
        binding.buttonStop.setOnClickListener { WorkManager.getInstance(applicationContext).cancelAllWork() }
    }

    private fun start() {
        val workRequest = PeriodicWorkRequestBuilder<ShowNotificationWorker>(15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }
}