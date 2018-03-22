package com.altintro.podium

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.a630465.podium.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.my_toolbar))
    }
}
