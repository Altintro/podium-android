package com.altintro.podium.interactor.getAll

import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletion

interface GetOneInteractor<T> {
    fun execute(objId:String, success: SuccessCompletion<T>, error: ErrorCompletion)
}