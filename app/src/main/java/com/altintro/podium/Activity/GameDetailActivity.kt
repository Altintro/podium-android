package com.altintro.podium.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.altintro.podium.R

import kotlinx.android.synthetic.main.activity_game_detail.*

class GameDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)
        setSupportActionBar(toolbar)

    }

}
