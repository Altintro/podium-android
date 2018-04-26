package com.altintro.podium.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.altintro.podium.R
import com.altintro.podium.WikiApiService
import com.altintro.podium.model.SignInType
import com.altintro.podium.router.Router
import com.altintro.podium.utils.PREFERENCES
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.joanzapata.iconify.widget.IconButton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_authentication.*
import java.util.*


class AuthenticationActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null
    private val TAG = AuthenticationActivity::class.qualifiedName
    private val router: Router = Router()
    private lateinit var prefs: SharedPreferences

    private val wikiApiService by lazy {
        WikiApiService.create()
    }

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        prefs = this.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

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
                checkTokenFacebook(accessToken.token)
            }

            override fun onCancel() {
                Log.d(TAG, "Facebook onCancel")
            }

            override fun onError(error: FacebookException?) {
                Log.d(TAG, "Facebook onError")
            }

        })
    }

    private fun checkTokenFacebook(accessToken: String){
        disposable = wikiApiService.facebookConnect(accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.d("Result", result.toString())
                            if(result.auth == true && result.type.equals(SignInType.signin.name)){
                                prefs.edit().putString("token", result.accessToken).apply()
                                router.goToMainActivityFromAuthentication(this)
                                finish()
                            }else if(result.auth == true && result.type.equals(SignInType.signup.name)){
                                router.goToRegisterActivityWithFacebook(this)
                                finish()
                            }
                        },
                        { error ->
                            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                        }
                )
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
