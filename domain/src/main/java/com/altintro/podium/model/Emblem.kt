package com.altintro.podium.model

import java.io.Serializable

data class Emblem(val id: String,
                  val name: String,
                  val sigil: String,
                  val description: String,
                  val requirements: String,
                  val users: List<User>) : Serializable

class Emblems (val emblems: MutableList<Emblem>): Aggregate<Emblem> {
    override fun count(): Int = emblems.size

    override fun all(): List<Emblem> = emblems

    override fun get(position: Int): Emblem {
        return emblems.get(position)
    }

    override fun add(element: Emblem) {
        emblems.add(element)
    }

    override fun delete(position: Int) {
        emblems.removeAt(position)
    }

    override fun delete(element: Emblem) {
        emblems.remove(element)
    }

}