package com.altintro.podium.Model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true) //Ignora las propiedades extras que pueda haber en el json que no necesitas sin que pinche

data class SportEntity (@SerializedName("_id")
        val id: String,
        val name: String,
        val image: String,
        val description: String,
        val rules: String,
        val popularity: Float
)