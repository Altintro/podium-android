package com.example.domain.model

import com.keepcoding.madridshops.domain.model.Aggregate
import java.io.Serializable
import java.util.*

data class User(val id: String,
                val name: String,
                val alias: String,
                val email: String,
                val image: String,
                val gender: Enums.Gender,
                val birthdate: Date,
                val latitude: Float,
                val longitude: Float,
                val ranking: UserRanking,
                val interests: Sports,
                val emblems: Emblems?,
                val played: Tournaments?,
                val playing: Tournaments?,
                val won: Tournaments?,
                val upcoming: Games?) : Serializable

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

