package com.altintro.podium.router

import android.content.Intent
import com.altintro.podium.Activity.MainActivity
import com.altintro.podium.activity.AuthenticationActivity
import com.altintro.podium.activity.RegisterActivity
import com.altintro.podium.utils.*


class Router {

    fun goToRegisterActivityWithEmail(activity: AuthenticationActivity) {
        activity.startActivity(Intent(activity, RegisterActivity::class.java).putExtra(INTENT_ACTION, CONNECT_WITH_EMAIL))
    }

    fun goToRegisterActivityWithFacebook(activity: AuthenticationActivity) {
        activity.startActivity(Intent(activity, RegisterActivity::class.java).putExtra(INTENT_ACTION, CONNECT_WITH_FACEBOOK_NEW_USER))
    }

    fun goToRegisterActivityWithGoogle(activity: AuthenticationActivity) {
        activity.startActivity(Intent(activity, RegisterActivity::class.java).putExtra(INTENT_ACTION, CONNECT_WITH_GOOGLE_NEW_USER))
    }

    fun goToMainActivityFromAuthentication(activity: AuthenticationActivity) {
        activity.startActivity(Intent(activity, MainActivity::class.java))
    }

    fun goToMainActivityFromRegister(activity: RegisterActivity) {
        activity.startActivity(Intent(activity, MainActivity::class.java))
    }

}