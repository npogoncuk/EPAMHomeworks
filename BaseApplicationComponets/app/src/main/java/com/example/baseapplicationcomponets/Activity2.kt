package com.example.baseapplicationcomponets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.baseapplicationcomponets.databinding.LayoutActivity2Binding
import kotlin.random.Random

class Activity2 : AppCompatActivity() {

    private lateinit var binding: LayoutActivity2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutActivity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGoForward.setOnClickListener {
            val intent = Intent(this, Activity3::class.java)
            startActivity(intent)
        }
        binding.buttonGoBack.setOnClickListener {
            finish()
        }

        binding.textViewActivity.text = getString(R.string.activity_text, "Activity2", Random.nextInt())
    }
}