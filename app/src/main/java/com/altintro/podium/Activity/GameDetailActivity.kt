package com.altintro.podium.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.altintro.podium.Adapter.ParticipantsRecyclerViewAdapter
import com.altintro.podium.R
import com.altintro.podium.model.Game

import kotlinx.android.synthetic.main.activity_game_detail.*
import kotlinx.android.synthetic.main.content_game_detail.*

class GameDetailActivity : AppCompatActivity() {

    companion object {
        val PARAM_GAME = "game"
    }

    lateinit var hardcodedLocations:ArrayList<String>
    private lateinit var adapter: ParticipantsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)
        setSupportActionBar(toolbar)

        //TODO: change locations to real when they come from the server
        hardcodedLocations = arrayListOf("Avenida Siempre Viva 1732, Springfield", "Greifswalder Strasse 152, Berlin", "Maggio 355, Buenos Aires", "Calle de Carretas 6, Madrid", "Avenida Touroperador Kouni 15, Las Palmas")

        title = "Game Detail"

        val game: Game = intent.getSerializableExtra(PARAM_GAME) as Game
        fillLayoutWithGameInfo(game)
    }

    private fun fillLayoutWithGameInfo(game:Game) {

        game_sport_name.text = game.sport.name
        game_description.text = game.description

        val randomLocation = hardcodedLocations[(Math.random() * (hardcodedLocations.count() - 1)).toInt()]
        game_location.text = randomLocation
        game_date.text = game.date.toString()

        if (game.participants.count() > 0) {
            participants_list_view.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)
            participants_list_view.itemAnimator = DefaultItemAnimator()

            adapter = ParticipantsRecyclerViewAdapter(baseContext, game.participants)
            //adapter.setClickListener(this)
            participants_list_view.adapter = adapter
        } else {
            participants_title_view.text = getString(R.string.no_participants_text)
        }
    }

}
