package io.zextech.authenticationapp.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import io.zextech.authenticationapp.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference(
    var context: Context
) {
    private val Context.dataStore by preferencesDataStore("TourGO")

    companion object {
        var TOKEN = stringPreferencesKey("token")
        val NAME = stringPreferencesKey("name")
        val EMAIL = stringPreferencesKey("email")
        val PASSWORD = stringPreferencesKey("password")
    }

    val authtoken: Flow<String?>
        get() = context.dataStore.data.map {
            it[TOKEN]
        }

    suspend fun getUserfromDataStore() = context.dataStore.data.map {
        User(
            it[NAME] ?: "",
            it[EMAIL] ?: "",
            it[PASSWORD] ?: ""
        )
    }

    suspend fun saveUsertoDataStore(user: User) {
        context.dataStore.edit {
            it[NAME] = user.name
            it[EMAIL] = user.email
            it[PASSWORD] = user.password
        }
    }

}
