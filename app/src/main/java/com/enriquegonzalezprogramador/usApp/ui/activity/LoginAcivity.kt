package com.enriquegonzalezprogramador.usApp.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.enriquegonzalezprogramador.usApp.R
import com.enriquegonzalezprogramador.usApp.database.AppDatabase
import com.enriquegonzalezprogramador.usApp.network.ApiClient
import com.enriquegonzalezprogramador.usApp.repository.UserRepositoryImpl
import com.enriquegonzalezprogramador.usApp.viewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        // Inicializar vistas
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        val apiService = ApiClient.apiService // Instancia de ApiService creada con ApiClient
        val userDao = AppDatabase.getInstance(applicationContext).userDao() // Instancia de UserDao obtenida de la base de datos


        // Inicializar ViewModel
        val userRepository = UserRepositoryImpl(userDao, apiService) // Aquí debes pasar la instancia de tu repositorio de usuarios
        loginViewModel = ViewModelProvider(this, LoginViewModel.Factory(userRepository))
            .get(LoginViewModel::class.java)

        // Configurar el clic del botón de inicio de sesión
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            Log.v("LOGIN", email )
            loginUser(email, password)
        }
    }

    private fun loginUser(email: String, password: String) {
        loginViewModel.loginUser(email, password) { success ->
            if (success) {
                Log.v("LOGIN", "Inicio de sesión exitoso")
                // Inicio de sesión exitoso, ir a la siguiente pantalla (p. ej., MainActivity)
                // Aquí debes iniciar la siguiente actividad o navegar a la siguiente pantalla
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
            } else {
                // Inicio de sesión fallido, mostrar un mensaje de error
                Toast.makeText(this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

