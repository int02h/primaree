package com.dpforge.primaree

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Process

fun Application.runIfPrimaryProcess(block: () -> Unit) {
    if (isPrimaryProcess) {
        block()
    }
}

val Application.isPrimaryProcess: Boolean
    get() = currentProcessFullName == packageName

val Application.currentProcessFullName: String?
    get() {
        val myPid = Process.myPid()
        val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?
        return am?.runningAppProcesses?.find { myPid == it.pid }?.processName
    }
