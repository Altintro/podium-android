package com.altintro.podium.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.altintro.podium.Fragment.CreateFragment
import com.altintro.podium.Fragment.HomeFragment
import com.altintro.podium.Fragment.ProfileFragment

class TabbarAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? = when (position) {
        0 -> HomeFragment.newInstance()
        1 -> CreateFragment.newInstance()
        2 -> ProfileFragment.newInstance()
        else -> null
    }

    override fun getCount(): Int = 3
}