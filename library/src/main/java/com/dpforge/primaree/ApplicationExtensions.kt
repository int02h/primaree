@file:JvmName("ApplicationExtensions")

package com.dpforge.primaree

import android.app.Application

/**
 * Run provided block only if current process is primary process
 */
inline fun Application.runIfPrimaryProcess(block: () -> Unit) {
    if (isPrimaryProcess) {
        block()
    }
}

/**
 * @return `true` if current process is primary process, `false` otherwise
 */
inline val Application.isPrimaryProcess: Boolean
    get() = Primaree.currentProcessFullName == packageName