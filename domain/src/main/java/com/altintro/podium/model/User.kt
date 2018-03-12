package com.altintro.podium.model

import com.google.gson.annotations.SerializedName
import com.keepcoding.madridshops.domain.model.Aggregate
import java.io.Serializable

data class User(@SerializedName("_id") val id: String,
                val name: String,
                val profilePic: String,
                val alias: String,
                val gender: Gender,
                val birthdate: String,
                val latitude: Float,
                val longitude: Float,
                val email: String,
                val ranking: List<UserRanking>,
                val interests: Sports,
                val emblems: List<Emblem>,
                val tournamentsPlayed: List<Tournament>,
                val tournamentsPlaying: List<Tournament>,
                val tournamentsUpcoming: List<Tournament>,
                val tournamentsWon: List<Tournament>,
                val gamesPlayed: List<Game>,
                val gamesPlaying: List<Game>,
                val gamesUpcoming: List<Game>,
                val gamesWon: List<Game>) : Serializable

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

