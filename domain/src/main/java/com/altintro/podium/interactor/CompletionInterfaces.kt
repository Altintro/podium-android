package com.altintro.podium.interactor

interface SuccessCompletion<T> {
    fun successCompletion(data : T)
}

interface SuccessCompletionBool {
    fun successCompletion(result: Boolean)
}

interface ErrorCompletion {
    fun errorCompletion(errorMessage : String)
}