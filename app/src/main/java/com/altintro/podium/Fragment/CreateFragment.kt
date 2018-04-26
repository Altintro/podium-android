package com.altintro.podium.Fragment

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
import com.altintro.podium.OrientationMode
import com.altintro.podium.adapter.RecyclerViewAdapter
import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletion
import com.altintro.podium.interactor.getAll.GetAllSportsInteractorImplementation
import com.altintro.podium.model.Sport
import com.altintro.podium.model.Sports
import com.altintro.podium.model.Users
import kotlinx.android.synthetic.main.fragment_create.view.*


class CreateFragment : Fragment(), RecyclerViewAdapter.ItemClickListener {

    private lateinit var fragmentView: View
    private lateinit var sport : Sport
    var sportItems = Sports(ArrayList<Sport>())

    companion object {
        fun newInstance(): CreateFragment {
            val fragment = CreateFragment()
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentView = inflater.inflate(R.layout.fragment_create, container, false)

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
            val title = fragmentView.findViewById<EditText>(R.id.et_title).text
            val date = fragmentView.findViewById<EditText>(R.id.et_when).text
            val details = fragmentView.findViewById<EditText>(R.id.et_details).text
            goToStep(3)
        }

    }

    fun bindRecyclerViewSport(sports: Sports,recyclerView: RecyclerView){

        val horizontalLayoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = horizontalLayoutManager

        val adapter = RecyclerViewAdapter(sports, OrientationMode.HORIZONTAL)
        adapter.setClickListener(this)
        recyclerView.adapter = adapter

    }

    fun bindRecyclerViewUser(users: Users, recyclerView: RecyclerView){

        val horizontalLayoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = horizontalLayoutManager

        val adapter = RecyclerViewAdapter(users, OrientationMode.VERTICAL)
        adapter.setClickListener(this)
        recyclerView.adapter = adapter

    }

    override fun onItemClick(view: View, position: Int, content: String) {
        goToStep(2)
        val sport = content
    }

    fun goToStep(step: Int){

        when (step) {
            1 -> showStep1()
            2 -> showStep2()
            3 -> showStep3()
        }
    }

    fun showStep1(){
        fragmentView.tv_title_step.text = getString(R.string.step1)
        fragmentView.view_step1.visibility = View.VISIBLE
        fragmentView.view_step2.visibility = View.GONE
        fragmentView.view_step3.visibility = View.GONE
    }

    fun showStep2(){
        fragmentView.tv_title_step.text = getString(R.string.step2)
        fragmentView.view_step2.visibility = View.VISIBLE
        fragmentView.view_step1.visibility = View.GONE
        fragmentView.view_step3.visibility = View.GONE
    }

    fun showStep3(){
        fragmentView.tv_title_step.text = getString(R.string.step3)
        fragmentView.view_step3.visibility = View.VISIBLE
        fragmentView.view_step1.visibility = View.GONE
        fragmentView.view_step2.visibility = View.GONE
    }


    //----------------------------------------------- CONNECTION WITH THE API ----------------------------------

    private fun getSports(recyclerView: RecyclerView) {

        val getAllSportsInteractor = GetAllSportsInteractorImplementation()
        getAllSportsInteractor.execute(success = object : SuccessCompletion<Sports> {
            override fun successCompletion(sports: Sports) {
                sportItems = sports
                bindRecyclerViewSport(sportItems,recyclerView = recyclerView)
            }

        }, error = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(activity!!, errorMessage, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun getUsers(recyclerView: RecyclerView) {

    }

}
