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

    private var disposable: Disposable? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //Get Games
        disposable = wikiApiService.getGames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                            val games:List<Game> = result.result
                            fillRecyclerViewWithGames(games)
                        }, { error ->
                            Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
                        }
                )
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    fun fillRecyclerViewWithGames(games:List<Game>) {

        val viewColors = ArrayList<Int>()
        viewColors.add(Color.BLUE)
        viewColors.add(Color.YELLOW)
        viewColors.add(Color.MAGENTA)
        viewColors.add(Color.RED)
        viewColors.add(Color.GREEN)

        val gameNames = ArrayList<String>()
        gameNames.add(games[0].name)
        gameNames.add(games[1].name)
        gameNames.add(games[2].name)
        gameNames.add(games[3].name)
        gameNames.add(games[4].name)

        val recyclerView: RecyclerView = view!!.findViewById(R.id.rv_games)
        val horizontalLayoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = horizontalLayoutManager
        adapter = MyRecyclerViewAdapter(activity!!, viewColors, gameNames)
        adapter.setClickListener(this)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(activity,"You clicked: " + adapter.getItem(position) + "on item position", Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
       /* if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }*/
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }
}
