package com.altintro.podium.custom

import android.content.Context

import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import com.altintro.podium.model.Game
import com.example.a630465.podium.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_participants.view.*

class ParticipantsView : RelativeLayout {

    constructor(context: Context) : this(context, null) {setupComponents()}
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0){setupComponents()}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){setupComponents()}

    private fun setupComponents(){
        View.inflate(context, R.layout.view_participants, this)
    }

    fun setImageForUser(game: Game){
        Log.d("CUSTOM VIEW", "GAME" + game.toString())
        
        Picasso.get().load(R.drawable.user1)
                .placeholder(R.drawable.loading).resize(150, 150).centerCrop()
                .into(iv_user1)

        Picasso.get().load(R.drawable.user2)
                .placeholder(R.drawable.loading).resize(150, 150).centerCrop()
                .into(iv_user2)

        Picasso.get().load(R.drawable.user3)
                .placeholder(R.drawable.loading).resize(150, 150).centerCrop()
                .into(iv_user3)
        }
    }
