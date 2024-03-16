package com.enriquegonzalezprogramador.usApp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.enriquegonzalezprogramador.usApp.model.User
import com.enriquegonzalezprogramador.usApp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {


    fun loginUser(email: String, password: String, onLoginResult: (Boolean) -> Unit) {
        // Lanzar una corrutina para realizar la operación de inicio de sesión en un hilo secundario
        viewModelScope.launch(Dispatchers.IO) {
            // Obtener el usuario de la base de datos
            val user = userRepository.loginUser(email, password)

            // Verificar si el usuario existe y si la contraseña coincide
            if (user != null && user.password == password) {
                // Inicio de sesión exitoso
                onLoginResult(true)
            } else {
                // Inicio de sesión fallido
                onLoginResult(false)
            }
        }
    }

    // Factory para crear instancias de LoginViewModel
    class Factory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LoginViewModel(userRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    // Método para registrar un nuevo usuario
    fun registerUser(username: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userRepository.registerUser(username, email, password)
            // Aquí puedes manejar la respuesta del registro de usuario, por ejemplo, navegación a la pantalla de inicio de sesión
        }
    }

    // Método para obtener todos los usuarios
    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val users = userRepository.getAllUsers()
            // Aquí puedes manejar la lista de usuarios obtenida, por ejemplo, actualizar la UI con la lista de usuarios
        }
    }

    // Método para verificar si el usuario está autenticado (por ejemplo, si ha iniciado sesión previamente)
    fun isAuthenticated(): Boolean {
        // Implementa la lógica necesaria para verificar si el usuario está autenticado
        // Por ejemplo, puedes comprobar si hay una sesión activa o si hay un token de acceso válido
        // En este ejemplo, simplemente devuelve falso
        return false
    }
}
