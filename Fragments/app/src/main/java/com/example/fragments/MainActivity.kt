package com.example.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

private const val FRAGMENT1_TOP_KEY = "FRAGMENT1_TOP_KEY"
private const val FIRST_START_OF_ACTIVITY = "FIRST_START_OF_ACTIVITY"

class MainActivity : AppCompatActivity() {

    private var fragment1Top = true
    private var firstStartOfActivity = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState?.also {
            fragment1Top = it.getBoolean(FRAGMENT1_TOP_KEY)
            firstStartOfActivity = it.getBoolean(FIRST_START_OF_ACTIVITY)
        }
        if (firstStartOfActivity) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frameLayoutTop,  if (fragment1Top) Fragment1.newInstance() else Fragment2.newInstance())
                .add(R.id.frameLayoutBottom, if (fragment1Top) Fragment2.newInstance() else Fragment1.newInstance())
                .commit()
        }

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(FRAGMENT1_TOP_KEY, supportFragmentManager.findFragmentById(R.id.frameLayoutTop)!!::class.java.name == Fragment1::class.java.name)
        outState.putBoolean(FIRST_START_OF_ACTIVITY, false)
    }

}