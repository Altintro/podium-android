package com.altintro.podium.model

import java.io.Serializable

data class Google(val sub: String,
                  val name: String,
                  val picture: String,
                  val email: String) : Serializable