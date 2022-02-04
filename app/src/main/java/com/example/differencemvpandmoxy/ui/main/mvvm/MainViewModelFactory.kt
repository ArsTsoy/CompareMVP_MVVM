package com.example.differencemvpandmoxy.ui.main.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.differencemvpandmoxy.model.UserService

class MainViewModelFactory(
    private val userService: UserService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(userService) as T
    }
}