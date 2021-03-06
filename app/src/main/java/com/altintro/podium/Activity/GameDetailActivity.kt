package com.altintro.podium.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.altintro.podium.Adapter.ParticipantsRecyclerViewAdapter
import com.altintro.podium.R
import com.altintro.podium.activity.AuthenticationActivity
import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletionBool
import com.altintro.podium.interactor.joinOneInteractor.JoinOneInteractor
import com.altintro.podium.interactor.joinOneInteractor.JoinOneInteractorImplementation
import com.altintro.podium.model.Game
import com.altintro.podium.model.User
import com.altintro.podium.utils.PREFERENCES

import kotlinx.android.synthetic.main.activity_game_detail.*
import kotlinx.android.synthetic.main.content_game_detail.*
import java.util.*
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask

class GameDetailActivity : AppCompatActivity() {

    companion object {
        val PARAM_GAME = "game"
    }

    // Container Activity or Fragment must implement this interface
    interface OnGameSubscriptionListener {
        fun refreshList()
    }

    lateinit var hardcodedLocations:ArrayList<String>
    private lateinit var adapter: ParticipantsRecyclerViewAdapter
    private lateinit var prefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)
        setSupportActionBar(toolbar)

        prefs = this.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

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

        join_game_button.setOnClickListener {
            joinGame(game)
            parent
        }
    }

    private fun joinGame(game:Game) {

        val token = prefs.getString("token", "")
        if(token.isEmpty()) {
            val intent = Intent(baseContext, AuthenticationActivity::class.java)
            startActivity(intent)
        } else {

            val joinGameInteractor:JoinOneInteractor = JoinOneInteractorImplementation()
            joinGameInteractor.execute(token, game.id, success = object: SuccessCompletionBool {

                override fun successCompletion(result: Boolean) {

                    Toast.makeText(baseContext, "Successfully registered to game", Toast.LENGTH_LONG).show()
                    join_game_button.visibility = View.INVISIBLE

                    val timer = Timer()
                    timer.schedule(timerTask { finish() }, 2000)

                }

            }, error = object: ErrorCompletion {

                override fun errorCompletion(errorMessage: String) {
                    Toast.makeText(baseContext, errorMessage, Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

}