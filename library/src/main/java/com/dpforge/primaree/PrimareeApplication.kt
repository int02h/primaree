package com.dpforge.primaree

import android.app.Application
import android.content.res.Configuration

/**
 * Base class for an application class that allows you to safely initialize your app
 * in a multi-process environment
 */
abstract class PrimareeApplication : Application() {

    private val shortProcessName: String by lazy {
        Primaree.currentProcessFullName?.substring(packageName.length) ?: UNKNOWN_PROCESS_NAME
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

    /**
     * [onCreate] for the primary process
     */
    abstract fun onPrimaryCreate()

    /**
     * [onCreate] for any secondary process
     *
     * @param name the name of the secondary process in the format it appears in AndroidManifest.xml
     */
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

    /**
     * [onConfigurationChanged] for the primary process
     */
    open fun onPrimaryConfigurationChanged() {}

    /**
     * [onConfigurationChanged] for any secondary process
     *
     * @param name the name of the secondary process in the format it appears in AndroidManifest.xml
     */
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

    /**
     * [onLowMemory] for the primary process
     */
    open fun onPrimaryLowMemory() {}

    /**
     * [onLowMemory] for any secondary process
     *
     * @param name the name of the secondary process in the format it appears in AndroidManifest.xml
     */
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

    /**
     * [onTrimMemory] for the primary process
     */
    open fun onPrimaryTrimMemory(level: Int) {}

    /**
     * [onTrimMemory] for any secondary process
     *
     * @param name the name of the secondary process in the format it appears in AndroidManifest.xml
     */
    open fun onSecondaryTrimMemory(name: String, level: Int) {}

    //endregion onTrimMemory

    companion object {
        const val UNKNOWN_PROCESS_NAME = "UNKNOWN"
    }

}