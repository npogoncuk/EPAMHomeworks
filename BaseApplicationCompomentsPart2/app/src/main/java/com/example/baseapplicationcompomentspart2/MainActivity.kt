package com.example.baseapplicationcompomentspart2

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.baseapplicationcompomentspart2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!haveReadContactsPermission(this)) {
            Toast.makeText(this, "You didn't grand the permission", Toast.LENGTH_SHORT).show()
            requestPermission()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(binding.frameLayout.id, ContactListFragment.newInstance())
                .commit()
        }


    }

    private fun requestPermission() {
        val permissionsToRequest = mutableListOf<String>()
        if (!haveReadContactsPermission(this)) permissionsToRequest.add(Manifest.permission.READ_CONTACTS)
        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(), 1)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    requestPermission()
                }
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.frameLayout.id, ContactListFragment.newInstance())
                        .commit()
                }
            }
        }
    }


}

fun haveReadContactsPermission(context: Context) =
    ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.READ_CONTACTS
    ) == PackageManager.PERMISSION_GRANTED