package com.example.baseapplicationcomponets

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.example.baseapplicationcomponets.databinding.ActivityServiceBinding

private const val LOG_SERVICE_ACTIVITY_TAG = "ServiceActivity"

class ServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServiceBinding

    private lateinit var counterService: CounterService
    private val connectionService = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.v(LOG_SERVICE_ACTIVITY_TAG, "CounterService onServiceConnected")
            val binder = service as CounterService.CounterServiceBinder
            counterService = binder.getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.v(LOG_SERVICE_ACTIVITY_TAG, "CounterService onServiceDisconnected")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonTimesAccess.setOnClickListener {
            val counter = counterService.howManyTimeWasAccessed()
            Toast.makeText(this, "Was accessed $counter times", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, CounterService::class.java).also {
            bindService(it, connectionService, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connectionService)
    }
}