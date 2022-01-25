package io.zextech.authenticationapp.repository

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import io.zextech.authenticationapp.model.User

const val DataStore_NAME = "PHONEBOOK"


class MainRepositories(private val context: Context) {
    companion object {
        var TOKEN = stringPreferencesKey("token")
        val NAME = stringPreferencesKey("name")
        val EMAIL = stringPreferencesKey("email")
        val PASSWORD = stringPreferencesKey("password")
    }

    suspend fun saveUser(user: User) {
//        context.dataStore.edit { users->
//            users[NAME] = user.name
//            users[EMAIL]= user.email
//            users[PASSWORD]= user.password
//        }

    }

//    override suspend fun getUser() = context.datastore.data.map { user ->
//        User(
//            user[NAME]!!,
//            user[EMAIL]!!,
//            user[PASSWORD]!!
//        )
//    }
}