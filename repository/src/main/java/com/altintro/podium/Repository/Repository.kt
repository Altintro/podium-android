package com.altintro.podium.Repository

interface Repository<T> {
    fun getAll(success: (items:List<T>) -> Unit, error: (errorMessage:String) -> Unit)
    fun getOne(objId:String, success: (item:T) -> Unit, error: (errorMessage:String) -> Unit)
}