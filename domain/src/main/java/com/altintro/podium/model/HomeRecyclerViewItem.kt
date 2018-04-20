package com.altintro.podium.model

import java.io.Serializable

data class HomeRecyclerViewItem(
                 val name: String,
                 val description: String,
                 val imageUrl: String) : Serializable {

    constructor(game:Game) : this(game.name, game.description ?: "", game.sport?.image ?: "")

    constructor(sport:Sport) : this(sport.name, sport.description, sport.image)

}