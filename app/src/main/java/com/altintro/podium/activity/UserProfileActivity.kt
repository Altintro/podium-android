package com.altintro.podium.activity

import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.altintro.podium.fragment.GenericFragmentDetailHeader
import com.altintro.podium.model.User
import com.example.a630465.podium.R
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

        // Configuro fragmento de cabecera
        val userHeaderFragment: GenericFragmentDetailHeader<User> = GenericFragmentDetailHeader.newInstance<User>(userExtra)
        fragmentManager.beginTransaction().replace(R.id.header_fragment, userHeaderFragment).commit()
    }
}
