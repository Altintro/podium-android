package com.altintro.podium.model

import java.io.Serializable

// Interface segregation

interface ReadAggregate<T> : Serializable {
    fun count(): Int
    fun all(): List<T>
    fun get(position: Int): T
}

interface WriteAggregate<T> : Serializable {
    fun add(element: T)
    fun delete(position: Int)
    fun delete(element: T)
}

interface Aggregate<T>: ReadAggregate<T>, WriteAggregate<T>
