package com.example.differencemvpandmoxy.ui.main.mvp

import com.example.differencemvpandmoxy.dto.User
import com.example.differencemvpandmoxy.model.UserService
import com.example.differencemvpandmoxy.model.UserService.Companion.FIRST_PAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope

@InjectViewState
class MainPresenter(
    private val userService: UserService
) : MvpPresenter<MainContract.View>(), MainContract.Presenter {

    init {
        loadUser(FIRST_PAGE)
    }

    //region Overridden Methods
    override fun loadUser(page: Int) {
        presenterScope.launch(Dispatchers.IO) {
            val users = userService.getUsers(page)

            withContext(Dispatchers.Main) {
                if (page == FIRST_PAGE) viewState.updateUserList(users)
                else viewState.addUsers(users)
            }
        }
    }

    override fun onUserClicked(user: User) {
        viewState.showToast(user)
    }
    //endregion


}