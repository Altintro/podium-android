package com.altintro.podium.model

import com.keepcoding.madridshops.domain.model.Aggregate
import java.io.Serializable

data class Tournament(val id: String,
                      val name: String,
                      val sport: Sport,
                      val compType: CompType,
                      val participants: List<Team>,
                      val players: List<User>,
                      val levelAverage: Level,
                      val starts: String,
                      val finishes: String,
                      val clasification: List<Clasification>,
                      val latitude: Float,
                      val longitude: Float,
                      val open: Boolean,
                      val rankPoints: Float,
                      val modality: Modality) : Serializable

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