package com.termproject.db.user

import androidx.lifecycle.LiveData

class UserRepository(private val userDAO: UserDAO) {
    suspend fun getUser(): User {
        return userDAO.getUser()
    }

    fun createNewUser(user: User) {
        userDAO.newUser(user)
    }

    fun reduceCredit(credit: Int) {
        userDAO.reduceCredit(credit)
    }

    fun updateUser(user: User) {
        userDAO.updateUser(user)
    }
}