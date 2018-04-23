package com.altintro.podium.Fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.altintro.podium.Adapter.MyRecyclerViewAdapter
import com.altintro.podium.WikiApiService
import com.altintro.podium.activity.AuthenticationActivity
import com.altintro.podium.model.Game
import com.altintro.podium.model.HomeRecyclerViewItem
import com.altintro.podium.model.Sport
import com.altintro.podium.utils.PREFERENCES
import com.example.a630465.podium.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class HomeFragment : Fragment(), MyRecyclerViewAdapter.ItemClickListener {

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    private val wikiApiService by lazy {
        WikiApiService.create()
    }

    private lateinit var adapter: MyRecyclerViewAdapter
    private lateinit var prefs: SharedPreferences
    private val TAG = HomeFragment::class.qualifiedName
    private lateinit var layourManager: GridLayoutManager

    private var gamesDisposable: Disposable? = null

    var gameItems: List<Game>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        prefs = this.activity!!.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

        val homeView = setupComponents(inflater, container)

        return homeView
    }

    private fun setupComponents(inflater: LayoutInflater, container: ViewGroup?): View? {
        val homeView = inflater.inflate(R.layout.fragment_home, container, false)

        val gamesRecyclerView = homeView!!.findViewById<RecyclerView>(R.id.rv_games)

        //Check if the games are already downloaded, so we dont load everytime the user changes a tab
        //TODO: add pull to refresh
        if (gameItems != null) {
            fillRecyclerViewWithItem(gameItems!!, gamesRecyclerView)
        } else {
            getGames(gamesRecyclerView)
        }
        return homeView
    }


    fun fillRecyclerViewWithItem(items:List<Game>, recyclerView: RecyclerView) {

        val gridLayourManager = GridLayoutManager(this.context,2)
        recyclerView.layoutManager = gridLayourManager
        adapter = MyRecyclerViewAdapter(activity!!, items)
        adapter.setClickListener(this)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(view: View, position: Int) {
        if(prefs.getString("token", "").isEmpty()) {
            val intent = Intent(activity, AuthenticationActivity::class.java)
            startActivity(intent)
        }
    }

    //----------------------------------------------- CONNECTION WITH THE API ----------------------------------

    private fun getGames(gamesRecyclerView: RecyclerView) {
        gamesDisposable = wikiApiService.getGames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    val games: List<Game> = result.result
                    gameItems = games
                    gamesDisposable!!.dispose()
                    fillRecyclerViewWithItem(gameItems!!, gamesRecyclerView)
                }, { error ->
                    Toast.makeText(activity!!, error.message, Toast.LENGTH_LONG).show()
                })
    }
}
