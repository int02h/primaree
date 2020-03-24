package com.dpforge.primaree

import android.app.Application
import android.content.res.Configuration

abstract class PrimareeApplication : Application() {

    private val shortProcessName: String by lazy {
        currentProcessFullName?.substring(packageName.length) ?: UNKNOWN_PROCESS_NAME
    }

    private val isPrimaryProcessCached: Boolean by lazy { isPrimaryProcess }

    //region onCreate

    final override fun onCreate() {
        super.onCreate()
        if (isPrimaryProcessCached) {
            onPrimaryCreate()
        } else {
            onSecondaryCreate(shortProcessName)
        }
    }

    abstract fun onPrimaryCreate()
    open fun onSecondaryCreate(name: String) {}

    //endregion

    //region onConfigurationChanged

    final override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (isPrimaryProcessCached) {
            onPrimaryConfigurationChanged()
        } else {
            onSecondaryConfigurationChanged(shortProcessName)
        }
    }

    open fun onPrimaryConfigurationChanged() {}
    open fun onSecondaryConfigurationChanged(name: String) {}

    //endregion

    //region onLowMemory

    final override fun onLowMemory() {
        super.onLowMemory()
        if (isPrimaryProcessCached) {
            onPrimaryLowMemory()
        } else {
            onSecondaryLowMemory(shortProcessName)
        }
    }

    open fun onPrimaryLowMemory() {}
    open fun onSecondaryLowMemory(name: String) {}

    //endregion

    //region onTrimMemory

    final override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (isPrimaryProcessCached) {
            onPrimaryTrimMemory(level)
        } else {
            onSecondaryTrimMemory(shortProcessName, level)
        }
    }

    open fun onPrimaryTrimMemory(level: Int) {}
    open fun onSecondaryTrimMemory(name: String, level: Int) {}

    //endregion onTrimMemory

    companion object {
        const val UNKNOWN_PROCESS_NAME = "UNKNOWN"
    }

}