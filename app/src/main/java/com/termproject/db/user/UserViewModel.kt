package com.termproject.db.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    init {
        val userDAO = UserRoomDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDAO)

    }

    fun createNewUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createNewUser(user)
        }
    }

    fun reduceCredit(amount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.reduceCredit(amount)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUsers()
        }
    }

    suspend fun getUser(): User {
        val deferred: Deferred<User> = viewModelScope.async {
            repository.getUser()
        }
        return deferred.await()

    }

}