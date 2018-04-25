package com.altintro.podium.Activity

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.altintro.podium.Fragment.CreateFragment
import com.altintro.podium.Fragment.HomeFragment
import com.altintro.podium.R
import com.altintro.podium.fragment.ProfileFragment
import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletion
import com.altintro.podium.interactor.getUser.GetMyUserInteractorImpl
import com.altintro.podium.interactor.getUser.GetUserInteractor
import com.altintro.podium.interactor.getUser.GetUserInteractorFakeImpl
import com.altintro.podium.model.User
import com.altintro.podium.router.Router
import com.altintro.podium.utils.PREFERENCES

class MainActivity : AppCompatActivity() {

    lateinit var homeFragment: HomeFragment
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Clear token
        prefs = this.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        //prefs.edit().putString("token", "").apply()

        setupComponents()
    }

    private fun setupComponents() {

        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        homeFragment = HomeFragment.newInstance()
        openFragment(homeFragment)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {

            R.id.navigation_home -> {

                openFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_create -> {

                val createFragment = CreateFragment.newInstance()
                openFragment(createFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {

                lateinit var loggedUser: User
                val userToken = prefs.getString("token", "")

                if (userToken != "") {
                    val getMyUserInteractor: GetMyUserInteractorImpl = GetMyUserInteractorImpl()
                    getMyUserInteractor.execute(userToken, success = object : SuccessCompletion<User> {
                        override fun successCompletion(data: User) {
                            loggedUser = data
                            val profileFragment = ProfileFragment.newInstance(loggedUser)
                            openFragment(profileFragment)
                        }
                    }, error = object : ErrorCompletion {
                        override fun errorCompletion(errorMessage: String) {
                        }

                    })
                } else {
                    val router: Router = Router()
                    // Todo: Redirigir al usuario a la pantalla de autenticación

                }

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        finish()
    }
}
