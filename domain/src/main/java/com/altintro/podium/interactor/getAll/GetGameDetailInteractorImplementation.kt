package com.altintro.podium.interactor.getAll

import com.altintro.podium.Model.GameEntity
import com.altintro.podium.Model.SportEntity
import com.altintro.podium.Model.UserEntity
import com.altintro.podium.Repository.Repository
import com.altintro.podium.Repository.RepositoryGamesImplementation
import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletion
import com.altintro.podium.model.*
import java.util.*

class GetGameDetailInteractorImplementation: GetOneInteractor<Game> {

    override fun execute(objId: String, success: SuccessCompletion<Game>, error: ErrorCompletion) {
        val repository: Repository<GameEntity> = RepositoryGamesImplementation()
        repository.getOne(objId, success = {
            val game:Game = mapGameEntityToGame(it)
            success.successCompletion(game)
        }, error = {
            error(it)
        })

    }

    private fun mapGameEntityToGame(gameEntity: GameEntity): Game {

        val game = Game(
                gameEntity.id,
                gameEntity.name,
                mapSportEntityToSport(gameEntity.sport),
                ArrayList<Tournament>(),
                mapUserEntitiesToUser(gameEntity.participants),
                null,
                null,
                gameEntity.concluded,
                gameEntity.date,    //TODO: Get real Date
                gameEntity.latitude,
                gameEntity.longitude,
                Modality.Individual, //TODO: Get real Modality
                gameEntity.open,
                Level.Beginner,   //TODO: Get real Level
                gameEntity.description
        )

        return game
    }

    private fun mapSportEntityToSport(sportEntity: SportEntity): Sport {

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

    private fun mapUserEntitiesToUser(userEntities: List<UserEntity>): List<User> {
        var usersList = mutableListOf<User>()

        userEntities.forEach {
            val user = User (
                    it.id,
                    it.name,
                    it.profilePic,
                    it.alias,
                    false,    //Not relevant for the Game
                    false,        //Not relevant for the Game
                    false,      //Not relevant for the Game
                    Gender.Male               //TODO: Get real gender when is sent from backend
            )
            usersList.add(user)
        }
        return usersList
    }
}