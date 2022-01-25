package io.zextech.authenticationapp.repository

import io.zextech.authenticationapp.model.User
import kotlinx.coroutines.flow.Flow

interface Abstract {
    suspend fun saveUser(user: User)

    suspend fun getUser(): Flow<User>
}