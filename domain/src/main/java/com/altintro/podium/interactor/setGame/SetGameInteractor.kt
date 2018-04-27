package com.altintro.podium.interactor.joinOneInteractor

import com.altintro.podium.Model.GameCreation
import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletionBool

interface SetGameInteractor {
    fun execute(token:String, item:GameCreation, success: SuccessCompletionBool, error: ErrorCompletion)
}