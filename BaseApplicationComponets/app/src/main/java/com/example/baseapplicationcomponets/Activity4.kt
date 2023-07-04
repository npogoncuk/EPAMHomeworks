package com.example.baseapplicationcomponets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.baseapplicationcomponets.databinding.LayoutActivity4Binding
import kotlin.random.Random

class Activity4 : AppCompatActivity() {

    private lateinit var binding: LayoutActivity4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutActivity4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGoForward.setOnClickListener {
            val intent = Intent(this, Activity1::class.java)
            startActivity(intent)
        }
        binding.buttonGoBack.setOnClickListener {
            finish()
        }

        binding.textViewActivity.text = getString(R.string.activity_text, "Activity4", Random.nextInt())
    }
}