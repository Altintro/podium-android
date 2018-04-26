package com.altintro.podium.fragment


import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.altintro.podium.R
import com.altintro.podium.model.*

class ProfileFragment : Fragment() {

    companion object {
        val ARG_CONTENT = "ARG_CONTENT"

        fun newInstance(content: User): ProfileFragment {
            val fragment = ProfileFragment()
            val arguments = Bundle()

            arguments.putSerializable(ARG_CONTENT, content)
            fragment.arguments = arguments

            return fragment
        }

    }

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var user : User
    private lateinit var fragmentView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        user = arguments?.getSerializable(ARG_CONTENT) as User
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (inflater != null) {

            fragmentView = inflater.inflate(R.layout.fragment_profile, container, false)

            // Configuro fragmento de datos de usuario
            val userHeaderFragment: GenericFragmentDetailHeader<User> = GenericFragmentDetailHeader.newInstance<User>(user)
            fragmentManager!!.beginTransaction().replace(R.id.header_fragment, userHeaderFragment).commit()

            // Configuro fragmento de intereses
            val userPreferencesFragment = GenericFragmentHorizontalRecyclerView.newInstance<Sport, Sports>(user.interests, "Ranking")
            fragmentManager!!.beginTransaction().replace(R.id.interests_fragment, userPreferencesFragment).commit()

            // Configuro fragmento de proximas partidas
            val userUpcomingFragment = GenericFragmentVerticalRecyclerView.newInstance<Game, Games>(user.gamesUpcoming, "Upcoming")
            fragmentManager!!.beginTransaction().replace(R.id.upcoming_fragment, userUpcomingFragment).commit()

            // Configuro fragmento de últimas partidas
            val userLastPlayedFragment = GenericFragmentVerticalRecyclerView.newInstance<Game, Games>(user.gamesPlayed, "Last Played")
            fragmentManager!!.beginTransaction().replace(R.id.last_played_fragment, userLastPlayedFragment).commit()
        }

        return fragmentView
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
/*        if (context is OnFragmentInteractionListener) {
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

