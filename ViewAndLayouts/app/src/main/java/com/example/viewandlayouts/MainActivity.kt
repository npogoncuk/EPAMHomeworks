package com.example.viewandlayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState ?: kotlin.run {
            supportFragmentManager.beginTransaction()
                .add(R.id.frameLayoutActivity, SpannableStringFragment.newInstance(), SpannableStringFragment.TAG)
                .commit()
        }
        setContentView(R.layout.activity_main)
    }

}