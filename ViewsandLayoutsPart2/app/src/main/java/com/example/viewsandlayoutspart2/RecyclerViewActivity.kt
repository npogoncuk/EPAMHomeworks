package com.example.viewsandlayoutspart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewsandlayoutspart2.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarLayout.toolbar.apply {
            title = "RecyclerView Task"
        })
    }
}