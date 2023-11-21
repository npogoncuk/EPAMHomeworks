package com.example.multithreadingpart2

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

private const val NOTIFICATION_ID = 0

class ShowNotificationWorker(
    private val context: Context,
    params: WorkerParameters
) : Worker(context, params) {
    override fun doWork(): Result {
        repeat(15) {
            val oneNotificationWorker = OneTimeWorkRequestBuilder<OneNotificationWorker>()
                .setInitialDelay( it.toLong(), TimeUnit.MINUTES)
                .build()
            WorkManager.getInstance(context).enqueue(oneNotificationWorker)
        }
        Log.d("Worker", "doWork: 15 workers")
        return Result.success()
    }

}

class OneNotificationWorker(private val context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        CoroutineScope(Dispatchers.Main).launch {
            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent =
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_star)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_text))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(context)) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    //ActivityCompat.requestPermissions(, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 0)
                    return@launch
                }
                notify(NOTIFICATION_ID, notificationBuilder.build())
            }

        }
        Log.d("Worker", "doWork: After Toast")
        return Result.success()
    }

}