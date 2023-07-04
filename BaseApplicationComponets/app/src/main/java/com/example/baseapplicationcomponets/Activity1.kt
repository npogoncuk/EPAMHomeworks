package com.example.baseapplicationcomponets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.baseapplicationcomponets.databinding.LayoutActivity1Binding
import kotlin.random.Random

class Activity1 : AppCompatActivity() {

    private lateinit var binding: LayoutActivity1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutActivity1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGoForward.setOnClickListener {
            val intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }
        binding.buttonGoBack.setOnClickListener {
            finish()
        }

        binding.textViewActivity.text = getString(R.string.activity_text, "Activity1", Random.nextInt())
    }
}