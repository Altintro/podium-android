package com.altIntro.podium

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.altintro.podium.activity.UserProfileActivity
import com.altintro.podium.interactor.ErrorCompletion
import com.altintro.podium.interactor.SuccessCompletion
import com.altintro.podium.interactor.getUser.GetUserInteractor
import com.altintro.podium.interactor.getUser.GetUserInteractorFakeImpl
import com.altintro.podium.model.User
import com.example.a630465.podium.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var userToShow: User

        val getUserInteractor : GetUserInteractor = GetUserInteractorFakeImpl()
        getUserInteractor.execute(success = object : SuccessCompletion<User> {
            override fun successCompletion(data: User) {
                userToShow = data
            }
        }, error = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

        button.setOnClickListener {
            startActivity(UserProfileActivity.intent(this, userToShow))
        }
    }
}
