package com.altintro.podium

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.altintro.podium.model.UserRegister
import com.altintro.podium.WikiApiService
import com.example.a630465.podium.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var token: String = ""
    lateinit var name : String
    lateinit var alias : String
    lateinit var email : String
    lateinit var pass : String
    lateinit var since : String
    lateinit var to : String
    lateinit var limit : String
    lateinit var checkName: String
    lateinit var checkAlias: String
    lateinit var sort: String

    private var disposable: Disposable? = null
    private val wikiApiService by lazy {
        WikiApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadComponents()

        btn_register.setOnClickListener {
            loadComponents()
            val user = UserRegister(name, alias, pass, email)
            register(user)
        }

        btn_login.setOnClickListener {
            loadComponents()
            val user = UserRegister("", "", pass, email)
            login(user)
        }

        btn_buscar_users.setOnClickListener{
            loadComponents()
            searchUsers(name,alias,limit,since,to,checkAlias,checkName,sort)
        }

        btn_buscar_games.setOnClickListener{
            loadComponents()
            searchGames(name,limit,sort)
        }


    }

    private fun loadComponents() {
        name = et_name.text.toString()
        alias = et_alias.text.toString()
        email = et_email.text.toString()
        pass = et_pass.text.toString()
        since = et_sinceSkip.text.toString()
        to = et_toSkip.text.toString()
        limit = et_limit.text.toString()
        checkAlias = if (checkbox_alias.isChecked() == true) "Alias" else ""
        checkName = if (checkbox_name.isChecked() == true) "Name" else ""
        sort = "name"
    }

    private fun register(userRegister: UserRegister){
        disposable = wikiApiService.register(userRegister)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            tv_result.text = result.token
                            token = result.token
                            Log.d("Result", "TOKEN " + result.token)
                        },
                        { error ->
                            Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
                        }
                )

    }

    private fun login(userRegister: UserRegister){
        disposable = wikiApiService.login(userRegister)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            tv_result.text = result.token
                            token = result.token
                            Log.d("Result", "TOKEN " + result.token)
                            et_email.text.clear()
                            et_pass.text.clear()

                        },
                        { error ->
                            Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
                        }
                )

    }

    private fun searchUsers(name: String, alias: String, limit: String, since: String, to: String, fields1: String, fields2: String, sort: String){
        disposable = wikiApiService.getUser(token,name,alias,limit,since,to,fields1,fields2,sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.d("Result", "There are:" + result.result.size + " Users")
                            //val users: Users = Mapper.userMapper(result.result)
                        },
                        { error ->
                            Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
                        }
                )

    }


    private fun searchGames(name: String, limit: String, sort: String){
        disposable = wikiApiService.getGame(token,name,limit,sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.d("Result", "There are:" + result.result.size + " Games")
                        },
                        { error ->
                            Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
                        }
                )

    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
