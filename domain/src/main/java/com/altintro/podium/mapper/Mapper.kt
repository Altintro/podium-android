package com.altintro.podium.mapper

import com.altintro.podium.model.User
import com.altintro.podium.model.Users
import java.util.ArrayList


object Mapper {

    fun userMapper(list: List<User>): Users {
        val tempList = ArrayList<User>()
        list.forEach {
                val user = User(
                        it.id,
                        it.name,
                        it.profilePic,
                        it.alias,
                        it.pass,
                        it.gender,
                        it.birthdate,
                        it.latitude,
                        it.longitude,
                        it.email,
                        it.ranking,
                        it.interests,
                        it.tournamentsPlayed,
                        it.tournamentsPlaying,
                        it.tournamentsUpcoming,
                        it.tournamentsWon,
                        it.gamesPlayed,
                        it.gamesPlaying,
                        it.gamesUpcoming,
                        it.gamesWon,
                        it.mergedWithGoogle,
                        it.hasPassword,
                        it.mergedWithFb)

                tempList.add(user)
        }

        val users = Users(tempList)
        return users

    }

}