package com.altintro.podium.Model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true) //Ignora las propiedades extras que pueda haber en el json que no necesitas sin que pinche

data class UserEntity (@SerializedName("_id")
        val id: String,
        val name: String,
        val profilePic: String,
        val alias: String,
        val pass: String = "",
        val birthdate: String = "",
        val latitude: Float = 0f,
        val longitude: Float = 0f,
        val email: String = "",
        val interests: List<SportEntity>? = null,
        val gamesPlayed: List<GameEntity>? = null,
        val gamesPlaying: List<GameEntity>? = null,
        val gamesUpcoming: List<GameEntity>? = null,
        val gamesWon: List<GameEntity>? = null,
        val mergedWithGoogle: Boolean,
        val hasPassword: Boolean,
        val mergedWithFb: Boolean
)