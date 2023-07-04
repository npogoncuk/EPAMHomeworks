package com.example.baseapplicationcomponets

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

private const val LOG_LIFECYCLE_TAG = "ActivityLifecycleListener"

class MyApplication : Application() {

    override fun onCreate() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Log.v(LOG_LIFECYCLE_TAG, "${activity::class.simpleName} ---> onActivityCreated")
            }

            override fun onActivityStarted(activity: Activity) {
                Log.v(LOG_LIFECYCLE_TAG, "${activity::class.simpleName} ---> onActivityStarted")
            }

            override fun onActivityResumed(activity: Activity) {
                Log.v(LOG_LIFECYCLE_TAG, "${activity::class.simpleName} ---> onActivityResumed")

            }

            override fun onActivityPaused(activity: Activity) {
                Log.v(LOG_LIFECYCLE_TAG, "${activity::class.simpleName} ---> onActivityPaused")
            }

            override fun onActivityStopped(activity: Activity) {
                Log.v(LOG_LIFECYCLE_TAG, "${activity::class.simpleName} ---> onActivityStopped")
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                Log.v(LOG_LIFECYCLE_TAG, "${activity::class.simpleName} ---> onActivitySaveInstanceState")
            }

            override fun onActivityDestroyed(activity: Activity) {
                Log.v(LOG_LIFECYCLE_TAG, "${activity::class.simpleName} ---> onActivityDestroyed")
            }

        })
        super.onCreate()
    }
}