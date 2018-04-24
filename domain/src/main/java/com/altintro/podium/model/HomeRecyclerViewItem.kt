package com.altintro.podium.model

import java.io.Serializable

data class HomeRecyclerViewItem(
                 val name: String,
                 val sport: Sport) : Serializable {

    constructor(game:Game) : this(game.name, game.sport!!)

}