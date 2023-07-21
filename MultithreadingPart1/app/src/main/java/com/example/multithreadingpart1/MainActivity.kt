package com.example.multithreadingpart1

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.multithreadingpart1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

private const val FILE_NAME = "my_file.txt"
private const val PSEUDO_LOADING_TIME = 3000L

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewSavedText.movementMethod = ScrollingMovementMethod()
        binding.buttonSaveToFile.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val textToSave = with(binding) {
                    String.format("Full name: %s\nEmail: %s\nPhone Number: %s", editTextFullName.text, editTextEmail.text, editTextPhoneNumber.text)
                }
                delay(PSEUDO_LOADING_TIME)
                saveToFile(textToSave)
            }
        }
        binding.buttonReadFromFile.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                delay(PSEUDO_LOADING_TIME)
                val text = readFromFile()
                runOnUiThread {
                    binding.textViewSavedText.text = text
                }
            }

        }
    }

    private fun readFromFile(fileName: String = FILE_NAME): String {
        val file = File(filesDir, fileName)
        return FileInputStream(file).use {
            String(it.readBytes())
        }
    }

    private fun saveToFile(text: String, fileName: String = FILE_NAME) {
        val file = File(filesDir, fileName)
        FileOutputStream(file).use {
            it.write(text.toByteArray())
        }
    }
}