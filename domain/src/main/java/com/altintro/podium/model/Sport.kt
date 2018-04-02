package com.altintro.podium.model

import java.io.Serializable

data class Sport(val id: String,
                 val name: String,
                 val image: String,
                 val description: String,
                 val rules: String,
                 val popularity: Float,
                 val activeTournaments: List<Tournament>,
                 val openTournaments: List<Tournament>,
                 val activeGames: List<Game>,
                 val openGames: List<Game>,
                 val ranking: List<User>) : Serializable

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