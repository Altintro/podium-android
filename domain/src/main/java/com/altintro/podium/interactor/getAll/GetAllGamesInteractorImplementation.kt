package com.altintro.podium.interactor.getAll

import com.altintro.podium.Model.GameEntity
import com.altintro.podium.Model.SportEntity
import com.altintro.podium.Repository.Repository
import com.altintro.podium.Repository.RepositoryGamesImplementation
import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletion
import com.altintro.podium.model.*
import java.util.*

class GetAllGamesInteractorImplementation: GetAllInteractor<List<Game>> {

    override fun execute(success: SuccessCompletion<List<Game>>, error: ErrorCompletion) {

        val repository: Repository<GameEntity> = RepositoryGamesImplementation()

        repository.getAll(success = {
            val games: List<Game> = mapEntityToGames(it)
            success.successCompletion(games)
        }, error = {
            error(it)
        })
    }

    private fun mapEntityToGames(list: List<GameEntity>): List<Game> {
        val gamesList = ArrayList<Game>()

        list.forEach {
9
            val game = Game(
                    it.id,
                    it.name,
                    mapSportEntityToSport(it.sport),
                    ArrayList<Tournament>(),
                    ArrayList<Team>(),
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
}
