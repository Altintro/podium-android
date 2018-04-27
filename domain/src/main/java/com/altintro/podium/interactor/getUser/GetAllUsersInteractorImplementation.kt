package com.altintro.podium.interactor.getAll

import com.altintro.podium.Model.SportEntity
import com.altintro.podium.Model.UserEntity
import com.altintro.podium.Repository.Repository
import com.altintro.podium.Repository.RepositorySportsImplementation
import com.altintro.podium.Repository.RepositoryUserImplementation
import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletion
import com.altintro.podium.model.*
import java.util.*

class GetAllUsersInteractorImplementation{

    fun execute(success: SuccessCompletion<Users>, error: ErrorCompletion) {

        val repository: Repository<UserEntity> = RepositoryUserImplementation()

        repository.getAll(success = {
            val users: Users = mapEntityToUsers(it)
            success.successCompletion(users)
        }, error = {
            error(it)
        })
    }

    private fun mapEntityToUsers(list: List<UserEntity>): Users {
        val usersList = Users(ArrayList<User>())

        list.forEach {
            val user = User(
                    it.id,
                    it.name,
                    it.profilePic,
                    it.alias,
                    false,
                    false,
                    false
            )

            usersList.add(user)
        }
        return usersList
    }
}
