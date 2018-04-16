package com.altintro.podium.activity

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Icon
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.altintro.podium.Activity.MainActivity
import com.altintro.podium.router.Router
import com.example.a630465.podium.R
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.joanzapata.iconify.widget.IconButton
import kotlinx.android.synthetic.main.activity_authentication.*
import java.util.*
import org.json.JSONObject
import org.json.JSONException


class AuthenticationActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null
    private val TAG = AuthenticationActivity::class.qualifiedName
    private val router: Router = Router()
    private lateinit var prefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        prefs = this.getSharedPreferences(TAG, 0)

        initializeFacebookData()
        setupComponents()
    }

    private fun setupComponents() {

        val btnLoginFacebook = findViewById<IconButton>(R.id.btn_login_facebook)
        val btnLoginGoogle = findViewById<IconButton>(R.id.btn_login_google)
        val btnLoginEmail = findViewById<IconButton>(R.id.btn_login_email)

        btnLoginFacebook.text = String.format(resources.getString(R.string.login_with_facebook), "    ");
        btnLoginGoogle.text = String.format(resources.getString(R.string.login_with_google), "  ");
        btnLoginEmail.text = String.format(resources.getString(R.string.login_with_email), "  ");

        btnLoginFacebook.setOnClickListener {
            connectWithFacebook()
        }

        btnLoginEmail.setOnClickListener  {
            connectWithEmail()
        }
    }

    private fun initializeFacebookData() {
        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                val accessToken = result.accessToken

                val request = GraphRequest.newMeRequest(accessToken, GraphRequest.GraphJSONObjectCallback { obj: JSONObject, response: GraphResponse ->
                    try{
                        Log.d(TAG, "Facebook data" + obj.toString())
                    } catch (e: JSONException){
                        e.printStackTrace()
                    }
                })

                request.executeAsync()
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



    private fun connectWithFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends", "email"));
        Log.d(TAG, "loginWithFacebook")
    }

    private fun connectWithEmail() {
        router.goToRegisterActivityWithEmail(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (callbackManager!!.onActivityResult(requestCode, resultCode, data)) {
            return
        }
    }


}
