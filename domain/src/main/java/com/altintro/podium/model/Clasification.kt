package com.altintro.podium.model
import java.io.Serializable

data class Clasification(val game: Game,
                         val round: Int,
                         val match: Int) : Serializable

class Clasifications (val clasifications: MutableList<Clasification>): Aggregate<Clasification> {
    override fun count(): Int = clasifications.size

    override fun all(): List<Clasification> = clasifications

    override fun get(position: Int): Clasification {
        return clasifications.get(position)
    }

    override fun add(element: Clasification) {
        clasifications.add(element)
    }

    override fun delete(position: Int) {
        clasifications.removeAt(position)
    }

    override fun delete(element: Clasification) {
        clasifications.remove(element)
    }

}