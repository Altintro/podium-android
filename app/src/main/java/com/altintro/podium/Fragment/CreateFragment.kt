package com.altintro.podium.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.altintro.podium.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.altintro.podium.Model.GameCreation
import com.altintro.podium.OrientationMode
import com.altintro.podium.adapter.RecyclerViewAdapter
import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletion
import com.altintro.podium.interactor.SuccessCompletionBool
import com.altintro.podium.interactor.getAll.GetAllSportsInteractorImplementation
import com.altintro.podium.interactor.getAll.GetAllUsersInteractorImplementation
import com.altintro.podium.interactor.joinOneInteractor.SetGameInteractor
import com.altintro.podium.interactor.joinOneInteractor.SetGameInteractorImplementation
import com.altintro.podium.model.Sport
import com.altintro.podium.model.Sports
import com.altintro.podium.model.User
import com.altintro.podium.model.Users
import com.altintro.podium.utils.PREFERENCES
import kotlinx.android.synthetic.main.content_game_detail.*
import kotlinx.android.synthetic.main.fragment_create.view.*
import java.util.*
import kotlin.concurrent.timerTask


class CreateFragment : Fragment(), RecyclerViewAdapter.ItemClickListener {

    private lateinit var prefs: SharedPreferences
    private lateinit var fragmentView: View
    private lateinit var sport: Sport
    private lateinit var user: User
    var sportItems = Sports(ArrayList<Sport>())
    var userItems = Users(ArrayList<User>())
    var token = ""
    var name_game : String = ""
    var date_game : String = ""
    var details_game : String = ""
    var sport_game : String = ""


    companion object {
        fun newInstance(): CreateFragment {
            val fragment = CreateFragment()
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentView = inflater.inflate(R.layout.fragment_create, container, false)
        prefs = context!!.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

        setupComponents()

        return fragmentView
    }


    private fun setupComponents() {

        val sportRecyclerView = fragmentView.findViewById<RecyclerView>(R.id.rv_sport)
        getSports(sportRecyclerView)
        goToStep(1)

        val userRecyclerView = fragmentView.findViewById<RecyclerView>(R.id.rv_persons)
        getUsers(userRecyclerView)


        fragmentView.findViewById<Button>(R.id.btn_continue).setOnClickListener {
            name_game = fragmentView.findViewById<EditText>(R.id.et_title).text.toString()
            date_game = fragmentView.findViewById<EditText>(R.id.et_when).text.toString()
            details_game = fragmentView.findViewById<EditText>(R.id.et_details).text.toString()
            goToStep(3)
        }



        fragmentView.findViewById<Button>(R.id.btn_create).setOnClickListener {
            val game = GameCreation(name_game,sport.id,details_game,date_game)
            token = prefs.getString("token", "")
            setGame(game,token)
        }

    }

    fun bindRecyclerViewSport(sports: Sports, recyclerView: RecyclerView) {

        val horizontalLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = horizontalLayoutManager

        val adapter = RecyclerViewAdapter(sports, OrientationMode.HORIZONTAL)
        adapter.setClickListener(this)
        recyclerView.adapter = adapter

    }

    fun bindRecyclerViewUser(users: Users, recyclerView: RecyclerView) {

        val horizontalLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = horizontalLayoutManager

        val adapter = RecyclerViewAdapter(users, OrientationMode.VERTICAL)
        adapter.setClickListener(this)
        recyclerView.adapter = adapter

    }

    override fun onItemClick(view: View, position: Int, content: String) {
        if(userItems.get(position).name == content){
            Toast.makeText(activity!!, "Usuario " + content, Toast.LENGTH_LONG).show()
            user = userItems.get(position)
        }else if(sportItems.get(position).name == content){
            sport = sportItems.get(position)
            goToStep(2)
        }
    }

    fun goToStep(step: Int) {

        when (step) {
            1 -> showStep1()
            2 -> showStep2()
            3 -> showStep3()
            4 -> showStep4()
        }
    }

    fun showStep1() {
        fragmentView.tv_title_step.text = getString(R.string.step1)
        fragmentView.view_step1.visibility = View.VISIBLE
        fragmentView.view_step2.visibility = View.GONE
        fragmentView.view_step3.visibility = View.GONE
    }

    fun showStep2() {
        fragmentView.tv_title_step.text = getString(R.string.step2)
        fragmentView.view_step2.visibility = View.VISIBLE
        fragmentView.view_step1.visibility = View.GONE
        fragmentView.view_step3.visibility = View.GONE
    }

    fun showStep3() {
        fragmentView.tv_title_step.text = getString(R.string.step3)
        fragmentView.view_step3.visibility = View.VISIBLE
        fragmentView.view_step1.visibility = View.GONE
        fragmentView.view_step2.visibility = View.GONE
    }

    fun showStep4() {
        fragmentView.tv_title_step.text = getString(R.string.step4)
        fragmentView.view_step3.visibility = View.GONE
        fragmentView.view_step1.visibility = View.GONE
        fragmentView.view_step2.visibility = View.GONE
    }


    //----------------------------------------------- CONNECTION WITH THE API ----------------------------------

    private fun getSports(recyclerView: RecyclerView) {

        val getAllSportsInteractor = GetAllSportsInteractorImplementation()
        getAllSportsInteractor.execute(success = object : SuccessCompletion<Sports> {
            override fun successCompletion(sports: Sports) {
                sportItems = sports
                bindRecyclerViewSport(sportItems, recyclerView = recyclerView)
            }

        }, error = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(activity!!, errorMessage, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun getUsers(recyclerView: RecyclerView) {

        val getAllUsersInteractorImplementation = GetAllUsersInteractorImplementation()
        getAllUsersInteractorImplementation.execute(success = object : SuccessCompletion<Users> {
            override fun successCompletion(users: Users) {
                userItems = users
                bindRecyclerViewUser(userItems, recyclerView = recyclerView)
            }

        }, error = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(activity!!, errorMessage, Toast.LENGTH_LONG).show()
            }

        })

    }


    private fun setGame(game: GameCreation, token: String) {
        val setGameInteractor: SetGameInteractor = SetGameInteractorImplementation()
        setGameInteractor.execute(token, game, success = object: SuccessCompletionBool {

            override fun successCompletion(result: Boolean) {
                Toast.makeText(context, "Game Create", Toast.LENGTH_LONG).show()
                goToStep(4)
            }

        }, error = object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }



}
