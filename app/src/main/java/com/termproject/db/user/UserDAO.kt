package com.termproject.db.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun newUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM users LIMIT 1")
    suspend fun getUser(): User
}