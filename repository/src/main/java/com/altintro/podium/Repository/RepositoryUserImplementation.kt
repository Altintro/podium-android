package com.altintro.podium.Repository

import com.altintro.podium.Model.GameEntity
import com.altintro.podium.Model.UserEntity
import com.altintro.podium.WikiApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RepositoryUserImplementation : Repository<UserEntity> {


    private var userDisposable: Disposable? = null

    private val wikiApiService by lazy {
        WikiApiService.create()
    }

    override fun getAll(success: (items: List<UserEntity>) -> Unit, error: (errorMessage: String) -> Unit) {


        userDisposable = wikiApiService.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    val users: List<UserEntity> = result.result
                    success(users)
                }, { error ->
                    error(error.localizedMessage)
                })

    }

    override fun getOne(objId:String, success: (item: UserEntity) -> Unit, error: (errorMessage: String) -> Unit) {

/*
        userDisposable = wikiApiService.getGameDetail(objId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    val gameEntity: GameEntity = result.result
                    success(gameEntity)
                }, { error ->
                    error(error.localizedMessage)
                })
*/
    }

    fun getMyProfile(token : String, success: (item: UserEntity) -> Unit, error: (errorMessage: String) -> Unit) {

        userDisposable = wikiApiService.getMyProfile(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    val userEntity: UserEntity = result.result
                    success(userEntity)
                }, { error ->
                    error(error.localizedMessage)
                })
    }



}