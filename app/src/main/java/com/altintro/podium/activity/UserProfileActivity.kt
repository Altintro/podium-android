package com.altintro.podium.activity

import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.altIntro.podium.R
import com.altintro.podium.fragment.GenericFragmentDetailHeader
import com.altintro.podium.fragment.GenericFragmentHorizontalRecyclerView
import com.altintro.podium.fragment.GenericFragmentVerticalRecyclerView
import com.altintro.podium.model.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : AppCompatActivity() {

    companion object {

        private val EXTRA_USER = "EXTRA_USER"
        lateinit var context: Context

        fun intent(context: Context, userToShow: User): Intent {

            val intent = Intent(context, UserProfileActivity::class.java)
            intent.putExtra(EXTRA_USER, userToShow)
            this.context = context
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val userExtra = intent.getSerializableExtra(EXTRA_USER) as User

        supportActionBar?.setTitle("User profile")

        // Configuro fragmento de datos de usuario
        val userHeaderFragment: GenericFragmentDetailHeader<User> = GenericFragmentDetailHeader.newInstance<User>(userExtra)
        fragmentManager.beginTransaction().replace(R.id.header_fragment, userHeaderFragment).commit()

        // Configuro fragmento de intereses
        val userPreferencesFragment = GenericFragmentHorizontalRecyclerView.newInstance<Sport, Sports>(userExtra.interests)
        fragmentManager.beginTransaction().replace(R.id.interests_fragment, userPreferencesFragment).commit()

        // Configuro fragmento de proximas partidas
        val userUpcomingFragment = GenericFragmentVerticalRecyclerView.newInstance<Game, Games>(userExtra.gamesUpcoming)
        fragmentManager.beginTransaction().replace(R.id.upcoming_fragment, userUpcomingFragment).commit()

        // Configuro fragmento de últimas partidas
        val userLastPlayedFragment = GenericFragmentVerticalRecyclerView.newInstance<Game, Games>(userExtra.gamesPlayed)
        fragmentManager.beginTransaction().replace(R.id.interests_fragment, userLastPlayedFragment).commit()
    }
}
