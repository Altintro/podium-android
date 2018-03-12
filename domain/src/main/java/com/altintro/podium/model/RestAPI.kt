package com.altintro.podium.model

import java.io.Serializable


data class ResponseAuth(val auth: Boolean,
                        val token: String) : Serializable

//-----------------------------------User-----------------------------------

data class UserRegister(var name: String,
                        val alias: String,
                        val pass: String,
                        val email: String) : Serializable

data class ResponseSearchUser(var success: Boolean,
                              var result: List<User>) : Serializable

//-----------------------------------Game-----------------------------------

data class GameCreation(var name: String)

data class ResponseSearchGame(var success: Boolean,
                              var result: List<Game>) : Serializable

//-----------------------------------Tournament-----------------------------------

data class TournamentCreation(var name: String,
                              var compType: String)

data class ResponseSearchTournament(var success: Boolean,
                                    var result: List<Tournament>) : Serializable



