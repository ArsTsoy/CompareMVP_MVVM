package com.example.differencemvpandmoxy.model

import com.example.differencemvpandmoxy.dto.User

interface UserService {
    companion object {
        internal const val FIRST_PAGE = 1
    }

    suspend fun getUsers(page: Int): List<User>

}