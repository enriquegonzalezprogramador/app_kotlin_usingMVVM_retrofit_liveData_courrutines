package com.enriquegonzalezprogramador.usApp.repository
import com.enriquegonzalezprogramador.usApp.model.User

interface UserRepository {
    suspend fun loginUser(email: String, password: String): User?
    suspend fun registerUser(username: String, email: String, password: String): User?
    suspend fun getAllUsers(): List<User>

}
