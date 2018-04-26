package com.altintro.podium.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.altintro.podium.R
import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletion
import com.altintro.podium.interactor.getAll.GetGameDetailInteractorImplementation
import com.altintro.podium.interactor.getAll.GetOneInteractor
import com.altintro.podium.model.Game

import kotlinx.android.synthetic.main.activity_game_detail.*
import kotlinx.android.synthetic.main.content_game_detail.*

class GameDetailActivity : AppCompatActivity() {

    companion object {
        val PARAM_GAME = "game"
    }

    lateinit var hardcodedLocations:ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)
        setSupportActionBar(toolbar)

        hardcodedLocations = arrayListOf("Avenida Siempre Viva 1732, Springfield", "Greifswalder Strasse 152, Berlin", "Maggio 355, Buenos Aires", "Calle de Carretas 6, Madrid", "Avenida Touroperador Kouni 15, Las Palmas")

        val game: Game = intent.getSerializableExtra(PARAM_GAME) as Game
        fillLayoutWithGameInfo(game)
    }

    private fun fillLayoutWithGameInfo(game:Game) {

        game_sport_name.text = game.sport.name
        game_description.text = game.description

        val randomLocation = hardcodedLocations[(Math.random() * (hardcodedLocations.count() - 1)).toInt()]
        game_location.text = randomLocation
        game_date.text = game.date.toString()
    }

}
