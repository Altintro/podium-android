package com.example.domain.mapper

import com.example.domain.model.User
import com.example.domain.model.Users
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
                        it.gender,
                        it.birthdate,
                        it.latitude,
                        it.longitude,
                        it.email,
                        it.ranking,
                        it.interests,
                        it.emblems,
                        it.tournamentsPlayed,
                        it.tournamentsPlaying,
                        it.tournamentsUpcoming,
                        it.tournamentsWon,
                        it.gamesPlayed,
                        it.gamesPlaying,
                        it.gamesUpcoming,
                        it.gamesWon)

                tempList.add(user)
        }

        val users = Users(tempList)
        return users

    }

}