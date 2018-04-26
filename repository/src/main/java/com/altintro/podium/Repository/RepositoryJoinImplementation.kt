package com.altintro.podium.Repository

import com.altintro.podium.Model.GameEntity
import com.altintro.podium.WikiApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RepositoryJoinImplementation: RepositoryNoClass {

    private var gamesDisposable: Disposable? = null

    private val wikiApiService by lazy {
        WikiApiService.create()
    }

    override fun joinOne(token: String, objId: String, success: (result: Boolean) -> Unit, error: (errorMessage: String) -> Unit) {

        gamesDisposable = wikiApiService.subscribeToGame(token,objId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    print("Holaa con resultado!")
                    success(result.success)
                }, { error ->
                    error(error.localizedMessage)
                })
    }
}