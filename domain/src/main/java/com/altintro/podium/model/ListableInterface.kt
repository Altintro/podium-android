package com.altintro.podium.model

import java.io.Serializable

interface Listable: Serializable {
    fun getImage(): String
    fun getTitle(): String
    fun getSubtitle(): String
}