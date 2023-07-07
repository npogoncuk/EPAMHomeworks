package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

private const val TOP_COLOUR_KEY = "TOP_COLOUR_KEY"
private const val BOTTOM_COLOUR_KEY = "BOTTOM_COLOUR_KEY"

class MainActivity : AppCompatActivity() {

    private var topColour: Int? = null
    private var bottomColour: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState?.also {
            topColour = savedInstanceState.getInt(TOP_COLOUR_KEY)
            bottomColour = savedInstanceState.getInt(BOTTOM_COLOUR_KEY)
            Log.d("mylog", "Activity restoredInstance $topColour")
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayoutTop, Fragment1.newInstance(topColour))
            .add(R.id.frameLayoutBottom, Fragment2.newInstance(bottomColour))
            .commit()

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        topColour = (supportFragmentManager.findFragmentById(R.id.frameLayoutTop) as ColourChanger).getColour()
        bottomColour = (supportFragmentManager.findFragmentById(R.id.frameLayoutBottom) as ColourChanger).getColour()
        outState.putInt(TOP_COLOUR_KEY, topColour!!)
        outState.putInt(BOTTOM_COLOUR_KEY, bottomColour!!)
        Log.d("mylog", "Activity savedInstance $topColour")
    }

}