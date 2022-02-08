package com.example.differencemvpandmoxy.ui.main.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.differencemvpandmoxy.dto.User
import com.example.differencemvpandmoxy.model.UserService
import com.example.differencemvpandmoxy.model.UserService.Companion.FIRST_PAGE
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.net.ConnectException

class MainViewModel(
    private val userService: UserService
) : ViewModel() {

    sealed class RVUpdater(val users: List<User>) {
        class UpdateAll(users: List<User>) : RVUpdater(users)
        class AddAll(users: List<User>) : RVUpdater(users)
    }

    //region Observers
    private val userList = MutableLiveData<RVUpdater>()
    val observerUserList: LiveData<RVUpdater> = userList

    private val chosenUser = MutableLiveData<User>()
    val observerChosenUser: LiveData<User> = chosenUser

    private val userList2 = MutableStateFlow(listOf<User>())
    val observerUserList2 = userList2.asStateFlow()

    private val user2 = MutableSharedFlow<User>()
    val observerUser2 = user2.asSharedFlow()
    //endregion

    init {
        loadUsers(FIRST_PAGE)
    }

    //region Actions
    fun loadUsers(page: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            runCatching { userService.getUsers(page) }
                .onSuccess {
                    if (page == FIRST_PAGE) {
                        userList.postValue(RVUpdater.UpdateAll(it))
                        withContext(Dispatchers.Main) { userList2.value = it }
                    } else {
                        userList.postValue(RVUpdater.AddAll(it))
                    }
                }
                .onFailure {

                }


        }


    }

    fun onUserClicked(user: User) {
//        chosenUser.postValue(user)
    }
    //endregion

}