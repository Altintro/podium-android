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
                val ranking: List<UserRanking>? = null,
                val interests: List<Sport>? = null,
                val emblems: List<Emblem>? = null,
                val tournamentsPlayed: List<Tournament>? = null,
                val tournamentsPlaying: List<Tournament>? = null,
                val tournamentsUpcoming: List<Tournament>? = null,
                val tournamentsWon: List<Tournament>? = null,
                val gamesPlayed: List<Game>? = null,
                val gamesPlaying: List<Game>? = null,
                val gamesUpcoming: List<Game>? = null,
                val gamesWon: List<Game>? = null,
                val fb: Facebook? = null,
                val hasPassword: Boolean? = false,
                val mergedWithFb: Boolean? = false) : Serializable

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

