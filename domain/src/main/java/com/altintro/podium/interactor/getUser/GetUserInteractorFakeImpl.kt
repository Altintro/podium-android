package com.altintro.podium.interactor.getUser

import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletion
import com.altintro.podium.model.*
import java.util.*
import kotlin.collections.ArrayList

class GetUserInteractorFakeImpl : GetUserInteractor {

    override fun execute(userId: String, success: SuccessCompletion<User>, error: ErrorCompletion) {
        // to play with errors initialize allIsOK with false
        var allIsOK = true

        if (allIsOK) {
            val user = loadFakeUser(userId)
            success.successCompletion(user)
        } else {
            error.errorCompletion("Error while accessing to the Fake Repository")
        }
    }

    fun loadFakeUser(userId: String): User {

        val user = User(
                        id = userId,
                        name = "John Doe",
                        profilePic = "https://i.stack.imgur.com/Lkn5a.png?s=328&g=1",
                        alias = "Jhonny",
                        mergedWithGoogle = false,
                        mergedWithFb = false,
                        hasPassword = false,
                        gender = Gender.Male,
                        pass = "",
                        birthdate = "01/01/1981",
                        latitude = 40.416775f,    // Madrid latitude
                        longitude =  -3.703790f,   // Madrid longitude
                        email = "a@b.c",
                        interests = fakeSports(7, "Sport #"),
                        gamesPlayed = fakeGames(15, "Played game #"),
                        gamesWon = ArrayList<Game>(),
                        gamesUpcoming = fakeGames(6, "Next game #"),
                        gamesPlaying = fakeGames(2, "Playing game #")
        )

        return user
    }


    private fun fakeGames(number: Int, title: String): Games {

        val games = Games(ArrayList<Game>())

        for (i in 1..number) {
            val game = Game(i.toString(),
                            name = title + i,
                            sport = Sport(id = "sport",
                                        name = "Sport",
                                        image = "https://i.pinimg.com/736x/65/ba/df/65badfdb809651268d56d7d1c2a06619--sport-icon-children-s.jpg"),
                            tournament = ArrayList<Tournament>(),
                            participants = ArrayList<User>(),
                            wins = Team("", ArrayList<User>(), "", ""),
                            loses = Team("", ArrayList<User>(), "", ""),
                            concluded = false,
                            date = Date("04/27/2018"),
                            latitude = 40.416775f,    // Madrid latitude
                            longitude = -3.703790f,   // Madrid longitude
                            modality = Modality.Individual,
                            open = true,
                            levelAverage = Level.Intermediate,
                            description = "Only a test game #" + i
            )
            games.add(game)
        }

        return games
    }

    private fun fakeSports(number: Int, title: String): Sports {

        val sports = Sports(ArrayList<Sport>())

        for (i in 1..number) {
            val sport = Sport(id = i.toString(),
                    name = title + i,
                    image = "https://i.pinimg.com/736x/65/ba/df/65badfdb809651268d56d7d1c2a06619--sport-icon-children-s.jpg")
            sports.add(sport)
        }

        return sports
    }
}

