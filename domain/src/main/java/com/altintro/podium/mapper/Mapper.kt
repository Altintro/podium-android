package com.altintro.podium.mapper

import com.altintro.podium.Model.GameEntity
import com.altintro.podium.Model.SportEntity
import com.altintro.podium.Model.UserEntity
import com.altintro.podium.model.*
import java.util.*
import kotlin.collections.ArrayList


object Mapper {

    fun userMapper(list: List<User>): Users {
        val tempList = ArrayList<User>()
        list.forEach {
                val user = User(
                        it.id,
                        it.name,
                        it.profilePic,
                        it.alias,
                        it.mergedWithGoogle,
                        it.hasPassword,
                        it.mergedWithFb,
                        it.gender,
                        it.pass,
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
                        it.gamesWon)

                tempList.add(user)
        }

        val users = Users(tempList)
        return users

    }

    fun mapUserEntityToUser(it: UserEntity): User {

        return User(
                id = it.id,
                name = it.name,
                profilePic = it.profilePic,
                alias = it.alias,
                pass = it.pass,
                birthdate = it.birthdate,
                latitude = it.latitude,
                longitude = it.longitude,
                email = it.email,
                interests = mapEntityToSports(it.interests),
                gamesPlayed = mapEntityToGames(it.gamesPlayed),
                gamesPlaying = mapEntityToGames(it.gamesPlaying),
                gamesUpcoming = mapEntityToGames(it.gamesUpcoming),
                gamesWon = ArrayList<Game>(),               //TODO: Get real amount of games won
                hasPassword = it.hasPassword,
                mergedWithFb = it.mergedWithFb,
                mergedWithGoogle = it.mergedWithGoogle)
    }

    fun mapEntityToSports(list: List<SportEntity>?): Sports? {
        val sportsList = Sports(ArrayList<Sport>())

        list?.forEach {

            val sport = Sport(
                    it.id,
                    it.name,
                    it.image
            )

            sportsList.add(sport)
        }
        return sportsList
    }

    fun mapEntityToGames(list: List<GameEntity>?): Games {
        val gamesList = Games(ArrayList<Game>())

        list?.forEach {
            9
            val game = Game(
                    it.id,
                    it.name,
                    mapSportEntityToSport(it.sport),
                    ArrayList<Tournament>(),
                    ArrayList<User>(),
                    null,
                    null,
                    it.concluded,
                    Date(),    //TODO: Get real Date
                    it.latitude,
                    it.longitude,
                    Modality.Individual, //TODO: Get real Modality
                    it.open,
                    Level.Beginner,   //TODO: Get real Level
                    ""      //TODO: Get real Description
            )

            gamesList.add(game)
        }
        return gamesList
    }

    fun mapSportEntityToSport(sportEntity: SportEntity): Sport {

        var description = ""
        if (sportEntity.description != null) {
            description = sportEntity.description
        }

        val sport = Sport(
                sportEntity.id,
                sportEntity.name,
                sportEntity.image,
                description,
                "",         //TODO: GET REAL Rules
                sportEntity.popularity
        )

        return sport
    }
}