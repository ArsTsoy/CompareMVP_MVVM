package com.example.differencemvpandmoxy.model

import com.example.differencemvpandmoxy.dto.User
import kotlinx.coroutines.flow.Flow

interface UserService {
    companion object {
        internal const val FIRST_PAGE = 1
    }

    suspend fun getUsers(page: Int): List<User>

    fun deleteUser(user: User): Flow<Int>

}