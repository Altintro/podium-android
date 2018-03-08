package com.example.domain.model

import com.keepcoding.madridshops.domain.model.Aggregate
import java.io.Serializable
import java.util.*

data class User(val id: String,
                val name: String,
                val image: String,
                val alias: String,
                val gender: Gender,
                val birthdate: String,
                val latitude: Float,
                val longitude: Float,
                val email: String,
                val ranking: UserRankings,
                val interests: Sports,
                val emblems: Emblems?,
                val tournamentsPlayed: Tournaments?,
                val tournamentsPlaying: Tournaments?,
                val tournamentsUpcoming: Tournaments?,
                val tournamentsWon: Tournaments?,
                val gamesPlayed: Games?,
                val gamesPlaying: Games?,
                val gamesUpcoming: Games?,
                val gamesWon: Games?) : Serializable

class Users(val users: MutableList<User>): Aggregate<User> {
    override fun count(): Int = users.size

    override fun all(): List<User> = users

    override fun get(position: Int): User {
        return users.get(position)
    }

    override fun add(element: User) {
        users.add(element)
    }

    override fun delete(position: Int) {
        users.removeAt(position)
    }

    override fun delete(element: User) {
        users.remove(element)
    }

}

