package com.altintro.podium.model

import com.google.gson.annotations.SerializedName


data class Sport(@SerializedName("_id")
                 val id: String,
                 val name: String,
                 val image: String,
                 val description: String = "",
                 val rules: String= "",
                 val popularity: Float = 0f,
                 val activeTournaments: List<Tournament>? = ArrayList<Tournament>(),
                 val openTournaments: List<Tournament>?  = ArrayList<Tournament>(),
                 val activeGames: List<Game>? = ArrayList<Game>(),
                 val openGames: List<Game>? = ArrayList<Game>(),
                 val ranking: List<User>? = ArrayList<User>()) : Listable {

    override fun get_Image(): String {
        return image
    }

    override fun getTitle(): String {
        return name
    }

    override fun getSubtitle(): String {
        return ""
    }
}

class Sports (val sports: MutableList<Sport>): Aggregate<Sport> {
    override fun count(): Int = sports.size

    override fun all(): List<Sport> = sports

    override fun get(position: Int): Sport {
        return sports.get(position)
    }

    override fun add(element: Sport) {
        sports.add(element)
    }

    override fun delete(position: Int) {
        sports.removeAt(position)
    }

    override fun delete(element: Sport) {
        sports.remove(element)
    }

}