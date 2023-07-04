package com.example.baseapplicationcomponets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.baseapplicationcomponets.databinding.LayoutActivity3Binding
import kotlin.random.Random

class Activity3 : AppCompatActivity() {

    private lateinit var binding: LayoutActivity3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutActivity3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGoForward.setOnClickListener {
            val intent = Intent(this, Activity4::class.java)
            startActivity(intent)
        }
        binding.buttonGoBack.setOnClickListener {
            finish()
        }

        binding.textViewActivity.text = getString(R.string.activity_text, "Activity3", Random.nextInt())
    }
}