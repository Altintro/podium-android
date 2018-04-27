package com.altintro.podium.Repository

import com.altintro.podium.Model.GameCreation

interface Repository<T> {
    fun getAll(success: (items:List<T>) -> Unit, error: (errorMessage:String) -> Unit)
    fun getOne(objId:String, success: (item:T) -> Unit, error: (errorMessage:String) -> Unit)
}

interface RepositoryNoClass {
    fun joinOne(token:String, objId:String, success: (result: Boolean) -> Unit, error: (errorMessage:String) -> Unit)
    fun setGame(token:String, item:GameCreation, success: (result: Boolean) -> Unit, error: (errorMessage: String) -> Unit)
}