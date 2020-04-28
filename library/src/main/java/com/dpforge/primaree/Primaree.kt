package com.dpforge.primaree

import android.app.Application
import android.os.Build
import android.os.Process
import android.os.StrictMode
import java.io.BufferedReader
import java.io.FileReader

fun Application.runIfPrimaryProcess(block: () -> Unit) {
    if (isPrimaryProcess) {
        block()
    }
}

val Application.isPrimaryProcess: Boolean
    get() = currentProcessFullName == packageName

val Application.currentProcessFullName: String?
    get() {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Application.getProcessName()
        } else {
            val myPid = Process.myPid()
            getProcessName(myPid)
        }
    }

private fun getProcessName(pid: Int): String? {
    if (pid <= 0) {
        return null
    }

    return runCatching {
        val bufferedReader: BufferedReader
        val oldThreadPolicy = StrictMode.allowThreadDiskReads()

        try {
            bufferedReader = BufferedReader(FileReader("/proc/$pid/cmdline"))
        } finally {
            StrictMode.setThreadPolicy(oldThreadPolicy)
        }

        val processName = bufferedReader.readLine().trim { it <= ' ' }

        runCatching { bufferedReader.close() }

        return@runCatching processName
    }.getOrNull()
}