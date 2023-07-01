package com.example.myapplication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import com.example.myapplication.stuff.CameraActivity

class MainActivity : CameraActivity() {

    override lateinit var previewView: PreviewView
    private var isFirstTimePermissionRequest = true
    private var hasBeenRedirectedToSetting = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        previewView = findViewById(R.id.preview_view)

        if (hasCameraPermission()) launchCamera() else requestPermission()
    }

    override fun onStart() {
        if (hasCameraPermission() && hasBeenRedirectedToSetting) {
            launchCamera()
            hasBeenRedirectedToSetting = false
        }
        super.onStart()
    }

    private fun hasCameraPermission() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission() {
        if (!isFirstTimePermissionRequest && !ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            hasBeenRedirectedToSetting = true
            openSettingsWithToast()
        } else {
            val permissionsToRequest = mutableListOf<String>()
            if (!hasCameraPermission()) permissionsToRequest.add(Manifest.permission.CAMERA)
            if (permissionsToRequest.isNotEmpty()) {
                isFirstTimePermissionRequest = false
                ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(), 1)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("Per", "onRequestPermissionsResult granted")
                    launchCamera()
                } else {
                    requestPermission()
                }
                return
            }
        }
    }

    private fun openSettingsWithToast() {
        openSettings()
        Toast.makeText(this, "Grant camera permission", Toast.LENGTH_LONG).show()
    }
}