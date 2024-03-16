package com.enriquegonzalezprogramador.usApp.network

import com.enriquegonzalezprogramador.usApp.model.User
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Interfaz que define los endpoints de la API REST.
 */
interface ApiService {

    /**
     * Método para realizar la operación de login.
     * @param username Nombre de usuario.
     * @param password Contraseña.
     * @return El usuario autenticado.
     */
    @GET("/login")
    suspend fun login(
        @Query("email") username: String,
        @Query("password") password: String
    ): Response<User> // Cambiar User por el tipo de respuesta que esperas del login

    /**
     * Método para realizar la operación de registro.
     * @param username Nombre de usuario.
     * @param email Dirección de correo electrónico.
     * @param password Contraseña.
     * @return El usuario registrado.
     */
    @POST("/users")
    suspend fun register(
        @Query("username") username: String,
        @Query("email") email: String,
        @Query("password") password: String
    ): User // Cambiar User por el tipo de respuesta que esperas del registro

    /**
     * Método para obtener la lista de usuarios.
     * @return Lista de usuarios.
     */
    @GET("/users")
    suspend fun getUsers(): List<User> // Cambiar User por el tipo de respuesta que esperas de la lista de usuarios
}
