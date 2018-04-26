package com.altintro.podium.model

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("_id") val id: String,
                val name: String,
                val profilePic: String,
                val alias: String,
                val mergedWithGoogle: Boolean,
                val hasPassword: Boolean,
                val mergedWithFb: Boolean,
                val gender: Gender? = null,
                val pass: String = "",
                val birthdate: String = "",
                val latitude: Float = 0f,
                val longitude: Float = 0f,
                val email: String = "",
                val ranking: List<UserRanking>? = null,
                val interests: Sports? = null,
                val tournamentsPlayed: List<Tournament>? = null,
                val tournamentsPlaying: List<Tournament>? = null,
                val tournamentsUpcoming: List<Tournament>? = null,
                val tournamentsWon: List<Tournament>? = null,
                val gamesPlayed: Games? = null,
                val gamesPlaying: Games??= null,
                val gamesUpcoming: Games? = null,
                val gamesWon: Games? = null) : Listable {

    override fun get_Image(): String {
        return profilePic
    }

    override fun getTitle(): String {
        return name
    }

    override fun getSubtitle(): String {

        if (gamesWon?.count() != 0) {
            //TODO: get real amount of different sports
            //var diferentSports = gamesWon?.games?.groupingBy { it.sport.name }?.eachCount()?.count()
            //return "${gamesWon?.count()} victories in ${diferentSports} diferent sports"
            return "${gamesWon?.count()} victories"
        } else {
            return "No victories"
        }
    }
}


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

