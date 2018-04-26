package com.altintro.podium.model

import java.io.Serializable

data class Facebook(val id: String,
                    val name: String,
                    val picture: String,
                    val email: String) : Serializable