package com.example.viewsandlayoutspart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewsandlayoutspart2.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarLayout.toolbar.apply {
            title = "ViewPager Task"
        })
    }
}