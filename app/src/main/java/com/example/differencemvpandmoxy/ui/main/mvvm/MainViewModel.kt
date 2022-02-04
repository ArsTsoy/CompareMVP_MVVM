package com.example.differencemvpandmoxy.ui.main.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.differencemvpandmoxy.dto.User
import com.example.differencemvpandmoxy.model.UserService
import com.example.differencemvpandmoxy.model.UserService.Companion.FIRST_PAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val userService: UserService
) : ViewModel() {


    //region Observers
    private val userList = MutableLiveData<List<User>>()
    val observerUserList: LiveData<List<User>> = userList

    private val chosenUser = MutableLiveData<User>()
    val observerChosenUser = chosenUser
    //endregion

    init {
        loadUsers(FIRST_PAGE)
    }

    //region Actions
    fun loadUsers(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val users = userService.getUsers(page)
            userList.postValue(users)
        }
    }

    fun onUserClicked(user: User) {
        chosenUser.postValue(user)
    }
    //endregion

}