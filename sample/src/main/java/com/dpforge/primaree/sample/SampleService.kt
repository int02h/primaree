package com.dpforge.primaree.sample

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import java.util.concurrent.TimeUnit

class SampleService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        log("SampleService.onCreate()")

        Handler().postDelayed({ stopSelf() }, TimeUnit.SECONDS.toMillis(5))
    }

    override fun onDestroy() {
        super.onDestroy()
        log("SampleService.onDestroy()")
    }

    companion object {
        fun start(context: Context) {
            context.startService(Intent(context, SampleService::class.java))
        }
    }

}