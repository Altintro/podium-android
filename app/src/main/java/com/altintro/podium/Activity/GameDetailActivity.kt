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

class GameDetailActivity : AppCompatActivity() {

    companion object {
        val PARAM_GAME_ID = "gameId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)
        setSupportActionBar(toolbar)

        val gameId: String = intent.getStringExtra(PARAM_GAME_ID)
        val getGameInteractor: GetOneInteractor<Game> = GetGameDetailInteractorImplementation()
        getGameInteractor.execute(gameId, success = object: SuccessCompletion<Game> {
            override fun successCompletion(game: Game) {
                fillLayoutWithGameInfo(game)
            }
        }, error = object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(baseContext, errorMessage, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun fillLayoutWithGameInfo(game:Game) {
        
    }

}
