package com.enriquegonzalezprogramador.usApp.repository

import android.util.Log
import com.enriquegonzalezprogramador.usApp.database.UserDao
import com.enriquegonzalezprogramador.usApp.model.User
import com.enriquegonzalezprogramador.usApp.network.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(private val userDao: UserDao, private val apiService: ApiService): UserRepository {

    // Método para iniciar sesión de usuario
    override suspend fun loginUser(email: String, password: String): User {
        return withContext(Dispatchers.IO) {
            // Llamada a la API para iniciar sesión
            val user = apiService.login(email, password)

            val us: User = user.body()!!;

          //  val responseBodyString = user.body()?.string()

               Log.v("VIEWMODEL", us.toString() )

            //  val userResponse = Gson().fromJson(responseBodyString, User::class.java)

            // Guardar usuario en la base de datos local
            userDao.insert(us)

            return@withContext us
        }
    }

    // Método para registrar un nuevo usuario
    override suspend fun registerUser(username: String, email: String, password: String): User {
        return withContext(Dispatchers.IO) {
            // Llamada a la API para registrar un nuevo usuario
            val user = apiService.register(username, email, password)

            // Guardar usuario en la base de datos local
            userDao.insert(user)

            return@withContext user
        }
    }

    // Método para iniciar sesión de usuario
    override suspend fun getAllUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            // Llamada a la API para iniciar sesión
            val users = apiService.getUsers();

            // Obtener usuarios en la base de datos local
            val localUsers = userDao.getAllUsers();

            return@withContext users
        }
    }


}