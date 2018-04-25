package com.altintro.podium.model


import com.google.gson.annotations.SerializedName
import java.util.*

data class Game(@SerializedName("_id") val id: String,
                val name: String,
                val sport: Sport,
                val tournament: List<Tournament>,
                val participants: List<Team>,
                val wins: Team?,
                val loses: Team?,
                val concluded: Boolean,
                val date: Date,
                val latitude: Float,
                val longitude: Float,
                val modality: Modality,
                val open: Boolean,
                val levelAverage: Level,
                val description: String) : Listable {

    override fun get_Image(): String {
        return ""
    }

    override fun getTitle(): String {
        return name
    }

    override fun getSubtitle(): String {
        return description
    }
}

class Games (val games: MutableList<Game>): Aggregate<Game> {
    override fun count(): Int = games.size

    override fun all(): List<Game> = games

    override fun get(position: Int): Game {
        return games.get(position)
    }

    override fun add(element: Game) {
        games.add(element)
    }

    override fun delete(position: Int) {
        games.removeAt(position)
    }

    override fun delete(element: Game) {
        games.remove(element)
    }

}