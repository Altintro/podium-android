package com.altintro.podium.activity

import android.content.SharedPreferences
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.altintro.podium.WikiApiService
import com.altintro.podium.model.UserRegister
import com.altintro.podium.router.Router
import com.altintro.podium.utils.*
import com.example.a630465.podium.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    private val wikiApiService by lazy {
        WikiApiService.create()
    }

    private var disposable: Disposable? = null
    private val ACTION by lazy { intent.getStringExtra(INTENT_ACTION) }
    private var email: String = ""
    private var name: String = ""
    private var alias: String = ""
    private lateinit var url: Uri
    private lateinit var prefs: SharedPreferences
    private val router: Router = Router()
    private val TAG = AuthenticationActivity::class.qualifiedName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        prefs = this.getSharedPreferences(TAG, 0)
        val action = getIntent().action

        if(action == INTENT_MAGIC_LINK){
            checkLogin(url)
        }

        setupComponents()
        loadView()
    }

    private fun setupComponents() {

        btn_continue_with_email.setOnClickListener{
            if(Utils().isEmailValid(it_email.text.toString())){
                container_inputEmail.visibility = View.GONE
                loader_indicator.visibility = View.VISIBLE
                email = it_email.text.toString()
                sendEmailConnect(email)
            }else{
                //Toast Error o cambiar color del input
            }
        }

        btn_register.setOnClickListener{
            name = it_fullName.text.toString()
            alias = it_alias.text.toString()
            val userRegister = UserRegister(name,alias,email)
            registerUser(userRegister)
        }
    }

    private fun loadView(){

        if(ACTION == CONNECT_WITH_EMAIL){
            container_inputEmail.visibility = View.VISIBLE

        }else if(ACTION == CONNECT_WITH_FACEBOOK_NEW_USER){
            container_register.visibility = View.VISIBLE

        }else if(ACTION == CONNECT_WITH_GOOGLE_NEW_USER) {
            container_register.visibility = View.VISIBLE
        }
    }


    private fun prepareRegisterViewFromEmail() {
        loader_indicator.visibility = View.GONE
        container_register.visibility = View.VISIBLE
        tv_fullName.visibility = View.VISIBLE
        itl_fullName.visibility = View.VISIBLE
    }

    private fun prepareMessageMagicLinkView() {
        loader_indicator.visibility = View.GONE
        container_register.visibility = View.GONE
        container_message.visibility = View.VISIBLE
    }

    //----------------------------------------------- CONNECTION WITH THE API ----------------------------------

    private fun checkLogin(url: Uri?) {
        val token = url.toString().substringAfterLast("?").substringAfterLast("token=")

        disposable = wikiApiService.tokens(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.d("Result", result.toString())
                            if(result.auth == true){
                                prefs.edit().putString("token", result.accessToken).apply()
                                router.goToMainActivityFromRegister(this)
                            }
                        },
                        { error ->
                            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                        }
                )

    }

    private fun registerUser(userRegister: UserRegister) {

        disposable = wikiApiService.emailRegister(userRegister)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.d("Result", result.toString())
                            if(result.auth == true) {
                                prepareMessageMagicLinkView()
                            }
                        },
                        { error ->
                            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                        }
                )
    }

    private fun sendEmailConnect(email: String) {

        disposable = wikiApiService.emailConnect(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            if(result.auth == false){
                                prepareRegisterViewFromEmail()
                            }else if(result.auth == true){
                                prefs.edit().putString("name", name).apply()
                                prefs.edit().putString("alias", alias).apply()
                                prefs.edit().putString("email", email).apply()
                                prepareMessageMagicLinkView()
                            }
                        },
                        { error ->
                            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                        }
                )
    }


    private fun refreshToken(refreshToken: String) {
        disposable = wikiApiService.refreshToken(refreshToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.d("Result", result.toString())
                            if(result.auth == true){
                                prefs.edit().putString("token", result.accessToken).apply()
                            }else{
                                Toast.makeText(this, "Access denied", Toast.LENGTH_LONG).show()
                            }

                        },
                        { error ->
                            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                        }
                )

    }

}
