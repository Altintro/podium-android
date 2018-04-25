package com.altintro.podium.Model

import java.io.Serializable


data class ResponseAuth(val auth: Boolean,
                        val token: String) : Serializable

data class ResponseEmailConnect(val auth: Boolean) : Serializable

data class ResponseTokens(val auth: Boolean,
                          val type: String,
                          val accessToken: String,
                          val refreshToken: String)

data class ResponseRefreshToken(val auth: Boolean,
                                val accessToken: String)

//-----------------------------------User-----------------------------------

data class UserRegister(var name: String,
                        val alias: String,
                        val email: String) : Serializable

data class ResponseSearchUser(var success: Boolean,
                              var result: List<UserEntity>) : Serializable

//-----------------------------------Game-----------------------------------

data class GameCreation(var name: String)

data class ResponseSearchGame(var result: List<GameEntity>) : Serializable

//-----------------------------------Sport-----------------------------------

data class ResponseSearchSport(var result: List<SportEntity>) : Serializable

//-----------------------------------Tournament-----------------------------------

//Coming next
/*
data class TournamentCreation(var name: String,
                              var compType: String)

data class ResponseSearchTournament(var success: Boolean,
                                    var result: List<Tournament>) : Serializable*/



