package com.dpforge.primaree.sample

import com.dpforge.primaree.PrimareeApplication

class App : PrimareeApplication() {

    override fun onPrimaryCreate() {
        log("App.onPrimaryCreate()")
    }

    override fun onSecondaryCreate(name: String) {
        super.onSecondaryCreate(name)
        log("App.onSecondaryCreate($name)")
    }

    override fun onPrimaryConfigurationChanged() {
        super.onPrimaryConfigurationChanged()
        log("App.onPrimaryConfigurationChanged()")
    }

    override fun onSecondaryConfigurationChanged(name: String) {
        super.onSecondaryConfigurationChanged(name)
        log("App.onSecondaryConfigurationChanged($name)")
    }
}