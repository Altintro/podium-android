package com.altintro.podium.interactor.joinOneInteractor
import com.altintro.podium.Model.GameCreation
import com.altintro.podium.Repository.RepositoryGamesImplementation
import com.altintro.podium.Repository.RepositoryJoinImplementation
import com.altintro.podium.Repository.RepositoryNoClass
import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletionBool

class SetGameInteractorImplementation: SetGameInteractor {

    override fun execute(token: String, item: GameCreation, success: SuccessCompletionBool, error: ErrorCompletion) {
        val repository: RepositoryNoClass = RepositoryJoinImplementation()
        repository.setGame(token, item, success = {
            success.successCompletion(it)
        }, error = {
            error.errorCompletion(it)
        })
    }
}