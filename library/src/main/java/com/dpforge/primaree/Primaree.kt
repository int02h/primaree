package com.dpforge.primaree

import android.app.Application
import android.os.Build
import android.os.Process
import android.os.StrictMode
import java.io.BufferedReader
import java.io.FileReader

object Primaree {

    val currentProcessFullName: String?
        get() {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Application.getProcessName()
            } else {
                readProcessName(Process.myPid())
            }
        }

    private fun readProcessName(pid: Int): String? {
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

}