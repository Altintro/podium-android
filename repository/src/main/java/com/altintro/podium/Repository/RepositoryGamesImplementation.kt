package com.altintro.podium.Repository

import com.altintro.podium.Model.GameEntity
import com.altintro.podium.WikiApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RepositoryGamesImplementation : Repository<GameEntity> {

    private var gamesDisposable: Disposable? = null

    private val wikiApiService by lazy {
        WikiApiService.create()
    }

    override fun getAll(success: (items: List<GameEntity>) -> Unit, error: (errorMessage: String) -> Unit) {

        gamesDisposable = wikiApiService.getGames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    val games: List<GameEntity> = result.result
                    success(games)
                }, { error ->
                    error(error.localizedMessage)
                })
    }
}