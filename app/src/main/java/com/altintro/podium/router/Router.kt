package com.altintro.podium.router

import android.app.Fragment
import android.content.Intent
import com.altintro.podium.Activity.MainActivity
import com.altintro.podium.activity.AuthenticationActivity
import com.altintro.podium.activity.RegisterActivity
import com.altintro.podium.utils.*


class Router {

    fun goToRegisterActivityWithEmail(activity: AuthenticationActivity) {
        activity.startActivity(Intent(activity, RegisterActivity::class.java).putExtra(INTENT_ACTION, CONNECT_WITH_EMAIL))
    }

    fun goToMainActivityFromRegister(activity: RegisterActivity) {
        activity.startActivity(Intent(activity, MainActivity::class.java))
    }

    fun goToMainActivityFromAuthentication(activity: AuthenticationActivity, caller:String) {
        val mainIntent =  Intent(activity, MainActivity::class.java)
        mainIntent.putExtra(MainActivity.SECTION_TO_GO, caller)
        activity.startActivity(mainIntent)
    }

    fun goToRegisterActivityWithFacebook(activity: AuthenticationActivity) {
        activity.startActivity(Intent(activity, RegisterActivity::class.java).putExtra(INTENT_ACTION, CONNECT_WITH_FACEBOOK_NEW_USER))
    }

    fun goToRegisterActivityWithGoogle(activity: AuthenticationActivity) {
        activity.startActivity(Intent(activity, RegisterActivity::class.java).putExtra(INTENT_ACTION, CONNECT_WITH_GOOGLE_NEW_USER))
    }

    fun goToAuthenticationActivityFromMain(activity: MainActivity, section:String) {
        val authenticationInten =  Intent(activity, AuthenticationActivity::class.java)
        authenticationInten.putExtra(AuthenticationActivity.ACTIVITY_CALLER, section)
        activity.startActivity(authenticationInten)
    }




}