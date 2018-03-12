package com.example.domain.model

import java.io.Serializable


data class ResponseAuth(val auth: Boolean,
                    val token: String) : Serializable

data class UserRegister(var name: String,
                        val alias: String,
                        val pass: String,
                        val email: String) : Serializable

data class GameCreation(var vame: String)

data class ResponseSearchUser(var success: Boolean,
                              var result: List<User>) : Serializable

data class ResponseSearchGame(var success: Boolean,
                              var result: List<Game>) : Serializable