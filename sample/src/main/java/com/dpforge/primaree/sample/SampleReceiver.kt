package com.dpforge.primaree.sample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class SampleReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        log("SampleReceiver.onReceive")
    }

    companion object {
        fun sendBroadcast(context: Context) {
            context.sendBroadcast(
                Intent("${context.packageName}.action.FOO").apply { setPackage(context.packageName) }
            )
        }
    }
}

