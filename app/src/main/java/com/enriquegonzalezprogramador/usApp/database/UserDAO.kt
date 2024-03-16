package com.enriquegonzalezprogramador.usApp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.enriquegonzalezprogramador.usApp.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>

    @Insert
    suspend fun insert(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteAllUsers()
}
