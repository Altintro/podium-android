package com.altintro.podium.interactor.joinOneInteractor
import com.altintro.podium.Repository.RepositoryJoinImplementation
import com.altintro.podium.Repository.RepositoryNoClass
import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletionBool

class JoinOneInteractorImplementation: JoinOneInteractor {

    override fun execute(token: String, objectId: String, success: SuccessCompletionBool, error: ErrorCompletion) {
        val repository: RepositoryNoClass = RepositoryJoinImplementation()
        repository.joinOne(token, objectId, success = {
            success.successCompletion(it)
        }, error = {
            error.errorCompletion(it)
        })
    }
}