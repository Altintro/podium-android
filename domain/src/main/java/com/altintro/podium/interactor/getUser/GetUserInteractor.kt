package com.altintro.podium.interactor.getUser

import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletion
import com.altintro.podium.model.User


interface GetUserInteractor {
    fun execute(userId: String, success: SuccessCompletion<User>, error: ErrorCompletion)
}