package com.altintro.podium.Activity

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.joanzapata.iconify.Iconify
import com.joanzapata.iconify.fonts.FontAwesomeModule
import com.joanzapata.iconify.fonts.MaterialModule
import com.joanzapata.iconify.fonts.TypiconsModule

class BaseApp: Application(){

    override fun onCreate() {
        super.onCreate()

        FacebookSdk.sdkInitialize(this)
        AppEventsLogger.activateApp(this)

        Iconify.with(FontAwesomeModule())
        Iconify.with(TypiconsModule())
        Iconify.with(MaterialModule())
    }
}