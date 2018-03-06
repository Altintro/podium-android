package com.example.domain.model

import com.keepcoding.madridshops.domain.model.Aggregate
import java.io.Serializable
import java.util.*

data class Tournament(val id: String,
                      val name: String,
                      val sport: Sport,
                      val compType: Enums.CompType,
                      val participants: Teams,
                      val players: Teams,
                      val games: Games,
                      val levelAverage: Enums.Level,
                      val starts: Date,
                      val finishes: Date,
                      val clasification: Clasifications,
                      val latitude: Float,
                      val longitude: Float,
                      val open: Boolean,
                      val rankPoints: Float) : Serializable

class Tournaments (val tournaments: MutableList<Tournament>): Aggregate<Tournament> {
    override fun count(): Int = tournaments.size

    override fun all(): List<Tournament> = tournaments

    override fun get(position: Int): Tournament {
        return tournaments.get(position)
    }

    override fun add(element: Tournament) {
        tournaments.add(element)
    }

    override fun delete(position: Int) {
        tournaments.removeAt(position)
    }

    override fun delete(element: Tournament) {
        tournaments.remove(element)
    }

}