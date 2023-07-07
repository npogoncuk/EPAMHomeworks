package com.example.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

private const val TOP_COLOUR_KEY = "TOP_COLOUR_KEY"
private const val BOTTOM_COLOUR_KEY = "BOTTOM_COLOUR_KEY"
private const val FRAGMENT1_TOP_KEY = "FRAGMENT1_TOP_KEY"

class MainActivity : AppCompatActivity() {

    private var topColour: Int? = null
    private var bottomColour: Int? = null
    private var fragment1Top = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState?.also {
            topColour = it.getInt(TOP_COLOUR_KEY)
            bottomColour = it.getInt(BOTTOM_COLOUR_KEY)
            fragment1Top = it.getBoolean(FRAGMENT1_TOP_KEY)
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayoutTop,  if (fragment1Top) Fragment1.newInstance(topColour) else Fragment2.newInstance(topColour))
            .add(R.id.frameLayoutBottom, if (fragment1Top) Fragment2.newInstance(bottomColour) else Fragment1.newInstance(bottomColour))
            .commit()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        topColour = (supportFragmentManager.findFragmentById(R.id.frameLayoutTop) as ColourChanger).getColour()
        bottomColour = (supportFragmentManager.findFragmentById(R.id.frameLayoutBottom) as ColourChanger).getColour()
        outState.putInt(TOP_COLOUR_KEY, topColour!!)
        outState.putInt(BOTTOM_COLOUR_KEY, bottomColour!!)
        outState.putBoolean(FRAGMENT1_TOP_KEY, supportFragmentManager.findFragmentById(R.id.frameLayoutTop)!!::class.java.name == Fragment1::class.java.name)
    }
}