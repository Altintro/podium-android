package com.altintro.podium.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.a630465.podium.R
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_registration.*
import java.util.*

class RegistrationActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null
    private val TAG = RegistrationActivity::class.qualifiedName
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        prefs = this.getSharedPreferences(TAG, 0)

        initializeFacebookData()
        setupComponents()
    }

    private fun initializeFacebookData() {
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                Log.d(TAG, "Facebook success: ")
                prefs.edit().putString("token", result.accessToken.token).apply()
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }

            override fun onCancel() {
                Log.d(TAG, "Facebook onCancel")
            }

            override fun onError(error: FacebookException?) {
                Log.d(TAG, "Facebook onError")
            }

        })
    }

    private fun setupComponents() {

        btn_login_facebook.text = String.format(resources.getString(R.string.login_with_facebook), "    ");
        btn_login_google.text = String.format(resources.getString(R.string.login_with_google), "  ");
        btn_login_email.text = String.format(resources.getString(R.string.login_with_email), "  ");

        btn_login_facebook.setOnClickListener {
            loginWithFacebook()
        }
    }

    private fun loginWithFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
        Log.d(TAG, "loginWithFacebook")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (callbackManager!!.onActivityResult(requestCode, resultCode, data)) {
            return
        }
    }


}
