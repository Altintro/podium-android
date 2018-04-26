package com.altintro.podium.interactor.getUser

import com.altintro.podium.Model.GameEntity
import com.altintro.podium.Model.UserEntity
import com.altintro.podium.Repository.Repository
import com.altintro.podium.Repository.RepositoryGamesImplementation
import com.altintro.podium.Repository.RepositoryUserImplementation
import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletion
import com.altintro.podium.mapper.Mapper.mapUserEntityToUser
import com.altintro.podium.model.*
import java.util.*
import kotlin.collections.ArrayList

class GetMyUserInteractorImpl : GetUserInteractor {

    override fun execute(userId: String, success: SuccessCompletion<User>, error: ErrorCompletion) {

        val repository = RepositoryUserImplementation()

        // Si estoy pidiendo el usuario que está logueado uso el token en lugar del userID
        val token = userId

        repository.getMyProfile(token, success = {
            val me: User = mapUserEntityToUser(it)
            success.successCompletion(me)
        }, error = {
            error(it)
        })
    }
}

