package com.altintro.podium

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.joanzapata.iconify.Iconify
import com.joanzapata.iconify.fonts.FontAwesomeModule
import com.joanzapata.iconify.fonts.MaterialModule
import com.joanzapata.iconify.fonts.TypiconsModule
import com.squareup.picasso.Picasso

class BaseApp: Application(){

    override fun onCreate() {
        super.onCreate()

        FacebookSdk.sdkInitialize(this)
        AppEventsLogger.activateApp(this)

        Iconify.with(FontAwesomeModule())
        Iconify.with(TypiconsModule())
        Iconify.with(MaterialModule())

        Picasso.get().isLoggingEnabled = true
        Picasso.get().setIndicatorsEnabled(false)
    }
}