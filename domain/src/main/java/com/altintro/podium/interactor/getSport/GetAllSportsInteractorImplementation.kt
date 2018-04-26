package com.altintro.podium.interactor.getAll

import com.altintro.podium.Model.SportEntity
import com.altintro.podium.Repository.Repository
import com.altintro.podium.Repository.RepositorySportsImplementation
import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletion
import com.altintro.podium.model.*
import java.util.*

class GetAllSportsInteractorImplementation{

    fun execute(success: SuccessCompletion<Sports>, error: ErrorCompletion) {

        val repository: Repository<SportEntity> = RepositorySportsImplementation()

        repository.getAll(success = {
            val sports: Sports = mapEntityToSports(it)
            success.successCompletion(sports)
        }, error = {
            error(it)
        })
    }

    private fun mapEntityToSports(list: List<SportEntity>): Sports {
        val sportsList = Sports(ArrayList<Sport>())

        list.forEach {
            9
            val sport = Sport(
                    it.id,
                    it.name,
                    it.image,
                    it.description,
                    it.rules,
                    it.popularity
            )

            sportsList.add(sport)
        }
        return sportsList
    }
}
