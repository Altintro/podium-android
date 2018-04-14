package com.altintro.podium.Activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.View
import com.altintro.podium.Fragment.CreateFragment
import com.altintro.podium.Fragment.HomeFragment
import com.altintro.podium.Fragment.ProfileFragment
import com.example.a630465.podium.R

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toolbar = findViewById(R.id.my_toolbar)
        toolbar.setTitle(getString(R.string.section_home_title))
        setSupportActionBar(toolbar)

        val bottomNavigation:BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val homeFragment = HomeFragment.newInstance()
        openFragment(homeFragment)

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {

            R.id.navigation_home -> {

                toolbar.setTitle(getString(R.string.section_home_title))

                val homeFragment = HomeFragment.newInstance()
                openFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_create -> {

                toolbar.setTitle(getString(R.string.section_create_title))

                val createFragment = CreateFragment.newInstance()
                openFragment(createFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {

                toolbar.setTitle(getString(R.string.section_profile_title))

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
