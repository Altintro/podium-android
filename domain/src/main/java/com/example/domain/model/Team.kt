package com.example.domain.model

import com.keepcoding.madridshops.domain.model.Aggregate
import java.io.Serializable

data class Team(val id: String,
                val players: Users,
                val name: String,
                val image: String) : Serializable

class Teams (val teams: MutableList<Team>): Aggregate<Team> {
    override fun count(): Int = teams.size

    override fun all(): List<Team> = teams

    override fun get(position: Int): Team {
        return teams.get(position)
    }

    override fun add(element: Team) {
        teams.add(element)
    }

    override fun delete(position: Int) {
        teams.removeAt(position)
    }

    override fun delete(element: Team) {
        teams.remove(element)
    }
}