package com.example.viewsandlayoutspart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewsandlayoutspart2.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarLayout.toolbar.apply {
            title = "WebView Task"
        })
    }
}