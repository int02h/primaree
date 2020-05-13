@file:Suppress("unused")
@file:JvmName("ApplicationExtensions")

package com.dpforge.primaree

import android.app.Application

fun Application.runIfPrimaryProcess(block: () -> Unit) {
    if (isPrimaryProcess) {
        block()
    }
}

val Application.isPrimaryProcess: Boolean
    get() = Primaree.currentProcessFullName == packageName