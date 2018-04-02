package com.altintro.podium.model

import java.io.Serializable

data class UserRanking(val sport: Sport,
                       val ranking: Int,
                       val points: Int) : Serializable

class UserRankings (val userRankings: MutableList<UserRanking>): Aggregate<UserRanking> {
    override fun count(): Int = userRankings.size

    override fun all(): List<UserRanking> = userRankings

    override fun get(position: Int): UserRanking {
        return userRankings.get(position)
    }

    override fun add(element: UserRanking) {
        userRankings.add(element)
    }

    override fun delete(position: Int) {
        userRankings.removeAt(position)
    }

    override fun delete(element: UserRanking) {
        userRankings.remove(element)
    }

}
