package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayoutTop, Fragment1.newInstance(null))
            .add(R.id.frameLayoutBottom, Fragment2.newInstance(null))
            .commit()
    }

}