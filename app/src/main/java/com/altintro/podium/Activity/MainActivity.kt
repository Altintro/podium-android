package com.altintro.podium.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import com.altintro.podium.Fragment.CreateFragment
import com.altintro.podium.Fragment.HomeFragment
import com.altintro.podium.Fragment.ProfileFragment
import com.example.a630465.podium.R
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

                val profileFragment = ProfileFragment.newInstance()
                openFragment(profileFragment)
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
}
