package com.altintro.podium.interactor

interface SuccessCompletion<T> {
    fun successCompletion(data : T)
}

interface ErrorCompletion {
    fun errorCompletion(errorMessage : String)
}