package com.example.baseapplicationcompomentspart2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources

class BatteryChargingReceiver(
    private val imageView: ImageView
) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action!!
        if (action == Intent.ACTION_POWER_CONNECTED) {
            imageView.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.baseline_battery_charging_full_96))
        }
        if (action == Intent.ACTION_POWER_DISCONNECTED) {
            imageView.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.baseline_battery_full_96))
        }
    }
}