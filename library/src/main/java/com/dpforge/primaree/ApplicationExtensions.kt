@file:JvmName("ApplicationExtensions")

package com.dpforge.primaree

import android.app.Application

inline fun Application.runIfPrimaryProcess(block: () -> Unit) {
    if (isPrimaryProcess) {
        block()
    }
}

inline val Application.isPrimaryProcess: Boolean
    get() = Primaree.currentProcessFullName == packageName