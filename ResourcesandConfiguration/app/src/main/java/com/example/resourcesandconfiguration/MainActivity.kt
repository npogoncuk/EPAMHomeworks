package com.example.resourcesandconfiguration

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d("mylog", (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE).toString())
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            Toast.makeText(this, "You are in landscape mode", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this, "You are in portrait mode", Toast.LENGTH_SHORT).show()
    }
}