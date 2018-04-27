package com.altintro.podium.custom

import android.content.Context

import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.altintro.podium.R
import com.altintro.podium.model.Game
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

        val imageViews = arrayListOf<ImageView>(iv_user1,iv_user2, iv_user3)
        var count = 0
        game.participants.forEach {
            Picasso.get().load(game.participants[0].profilePic)
                    .placeholder(R.drawable.loading).resize(100, 100).centerCrop()
                    .into(imageViews[count])
            count++
        }
    }

}
