package com.example.domain.model

import com.keepcoding.madridshops.domain.model.Aggregate
import java.io.Serializable
import java.util.*

data class Emblem(val id: String,
                  val name: String,
                  val sigil: String,
                  val description: String,
                  val requirements: String,
                  val users: Users) : Serializable

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