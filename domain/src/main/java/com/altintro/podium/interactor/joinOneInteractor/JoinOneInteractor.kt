package com.altintro.podium.interactor.joinOneInteractor

import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletionBool

interface JoinOneInteractor {
    fun execute(token:String, objectId:String, success: SuccessCompletionBool, error: ErrorCompletion)
}