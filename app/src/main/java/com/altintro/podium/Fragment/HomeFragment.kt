package com.altintro.podium.Fragment

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.altintro.podium.Adapter.MyRecyclerViewAdapter
import com.altintro.podium.WikiApiService
import com.altintro.podium.model.Game
import com.altintro.podium.model.HomeRecyclerViewItem
import com.altintro.podium.model.Sport
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

    lateinit var adapter: MyRecyclerViewAdapter

    private var gamesDisposable: Disposable? = null
    private var sportsDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //Get Games
        gamesDisposable = wikiApiService.getGames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                            val games:List<Game> = result.result
                            val recyclerViewItems:List<HomeRecyclerViewItem> = games.map { HomeRecyclerViewItem(it)}
                            val recyclerView = view!!.findViewById<RecyclerView>(R.id.rv_games)
                            fillRecyclerViewWithItem(recyclerViewItems, recyclerView)
                        }, { error ->
                            Toast.makeText(activity!!, error.message, Toast.LENGTH_LONG).show()
                        }
                )

        //Get Sports
        sportsDisposable = wikiApiService.getSports()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    val sports:List<Sport> = result.result
                    val recyclerViewItems:List<HomeRecyclerViewItem> = sports.map { HomeRecyclerViewItem(it)}
                    val recyclerView = view!!.findViewById<RecyclerView>(R.id.rv_sports)

                    fillRecyclerViewWithItem(recyclerViewItems, recyclerView)
                }, { error ->
                    Toast.makeText(activity!!, error.message, Toast.LENGTH_LONG).show()
                }
                )

        val homeView = inflater.inflate(R.layout.fragment_home, container, false)

        return homeView
    }

    fun fillRecyclerViewWithItem(items:List<HomeRecyclerViewItem>, recyclerView: RecyclerView) {

        val horizontalLayoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = horizontalLayoutManager
        adapter = MyRecyclerViewAdapter(activity!!, items)
        adapter.setClickListener(this)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(activity,"You clicked: " + adapter.getItem(position) + "on item position", Toast.LENGTH_SHORT).show()
    }
}
