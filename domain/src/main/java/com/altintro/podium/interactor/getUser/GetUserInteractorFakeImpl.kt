package com.altintro.podium.interactor.getUser

import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletion
import com.altintro.podium.model.*

class GetUserInteractorFakeImpl : GetUserInteractor {
    override fun execute(success: SuccessCompletion<User>, error: ErrorCompletion) {


        // to play with errors initialize allIsOK with false
        var allIsOK = true

        if (allIsOK) {
            val user = createFakeUser()
            success.successCompletion(user)
        } else {
            error.errorCompletion("Error while accessing to the Fake Repository")
        }
    }

    fun createFakeUser(): User {

        val user = User(
                "a1b2c3d4",
                "John Doe",
                "https://i.stack.imgur.com/Lkn5a.png?s=328&g=1",
                "Jhonny",
                latitude = 40.416775f,    // Madrid latitude
                longitude = -3.703790f,   // Madrid longitude
                gamesPlayed = fakeGames(15, "Played game #"),
                gamesWon = fakeGames(4, "Won game #"),
                gamesUpcoming = fakeGames(6, "Next game #"),
                gamesPlaying = fakeGames(2, "Playing game #"))

        return user
    }

    private fun fakeGames(number: Int, title: String): List<Game> {

        val games = ArrayList<Game>()

        for (i in 0..number) {
            val game = Game(i.toString(), name = title + i)
            games.add(game)
        }

        return games
    }
}