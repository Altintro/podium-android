package com.altintro.podium.Activity

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.Toolbar
import com.altintro.podium.Fragment.CreateFragment
import com.altintro.podium.Fragment.HomeFragment
import com.altintro.podium.fragment.ProfileFragment
import com.altIntro.podium.R
import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletion
import com.altintro.podium.interactor.getUser.GetUserInteractor
import com.altintro.podium.interactor.getUser.GetUserInteractorFakeImpl
import com.altintro.podium.model.User
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    lateinit var homeFragment: HomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

                // ToDo: Now, user logged in the app is obtained using FakeImplementation of GetUserInteractor
                val getUserInteractor : GetUserInteractor = GetUserInteractorFakeImpl()
                getUserInteractor.execute(userId = "a1b2c3d4", success = object : SuccessCompletion<User> {
                    override fun successCompletion(data: User) {
                        loggedUser = data
                        val profileFragment = ProfileFragment.newInstance(loggedUser)
                        openFragment(profileFragment)
                    }
                }, error = object : ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                    }

                })

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {

        fragmentManager.beginTransaction().
        replace(R.id.container, fragment).
        addToBackStack(null).commit()
    }

    override fun onBackPressed() {
        finish()
    }
}
