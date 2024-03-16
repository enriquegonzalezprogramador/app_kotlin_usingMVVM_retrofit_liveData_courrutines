package com.enriquegonzalezprogramador.usApp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.enriquegonzalezprogramador.usApp.model.User

// Anotación para indicar que esta clase es una base de datos Room y definir las entidades asociadas y su versión
@Database(entities = [User::class], version = 1, exportSchema = false) // Añadido exportSchema = false
abstract class AppDatabase : RoomDatabase() {

    // Método abstracto que proporciona una instancia del UserDao
    abstract fun userDao(): UserDao

    // Companion object para manejar la creación y acceso a la base de datos
    companion object {
        // Volátil para asegurar que la instancia de la base de datos sea siempre actualizada y visible a todos los hilos
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Método estático para obtener una instancia de la base de datos
        fun getInstance(context: Context): AppDatabase {
            // Verificar si la instancia de la base de datos ya existe
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database" // Nombre de la base de datos
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}



