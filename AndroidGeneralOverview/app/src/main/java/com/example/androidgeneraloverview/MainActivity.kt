package com.example.androidgeneraloverview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appInfo = "${BuildConfig.BUILD_TYPE} ${BuildConfig.FLAVOR} ${BuildConfig.VERSION_NAME} ${BuildConfig.BASE_URL}"
        Log.d("appInfo", appInfo)
    }
}