package com.example.differencemvpandmoxy.ui.main.mvp

import com.example.differencemvpandmoxy.dto.User
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MainContract {

    @StateStrategyType(AddToEndSingleStrategy::class)
    interface View: MvpView {

        fun updateUserList(users: List<User>)

        fun addUsers(users: List<User>)

        fun showToast(user: User)
    }

    interface Presenter {
        fun loadUser(page: Int)

        fun onUserClicked(user: User)
    }
}