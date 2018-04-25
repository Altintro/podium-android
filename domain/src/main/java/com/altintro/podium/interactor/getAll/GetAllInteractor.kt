package com.altintro.podium.interactor.getAll

import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletion

interface GetAllInteractor<T> {
    fun execute(success: SuccessCompletion<T>, error: ErrorCompletion)
}