package io.zextech.authenticationapp.viewmodel

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.zextech.authenticationapp.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    companion object {
        var TOKEN = stringPreferencesKey("token")
        val NAME = stringPreferencesKey("name")
        val EMAIL = stringPreferencesKey("email")
        val PASSWORD = stringPreferencesKey("password")
    }

    var token: MutableLiveData<String> = MutableLiveData("")
    var name: MutableLiveData<String> = MutableLiveData("")
    var email: MutableLiveData<String> = MutableLiveData("")
    var password: MutableLiveData<String> = MutableLiveData("")
    var user: MutableLiveData<User> = MutableLiveData()

    fun saveData() {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

    fun retrieveDate() {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
}