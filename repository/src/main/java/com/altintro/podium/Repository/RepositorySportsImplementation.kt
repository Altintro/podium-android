package com.altintro.podium.Repository

import com.altintro.podium.Model.GameEntity
import com.altintro.podium.Model.SportEntity
import com.altintro.podium.WikiApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RepositorySportsImplementation : Repository<SportEntity> {

    private var sportsDisposable: Disposable? = null

    private val wikiApiService by lazy {
        WikiApiService.create()
    }

    override fun getAll(success: (items: List<SportEntity>) -> Unit, error: (errorMessage: String) -> Unit) {

        sportsDisposable = wikiApiService.getSports()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    val sports: List<SportEntity> = result.result
                    success(sports)
                }, { error ->
                    error(error.localizedMessage)
                })
    }
}