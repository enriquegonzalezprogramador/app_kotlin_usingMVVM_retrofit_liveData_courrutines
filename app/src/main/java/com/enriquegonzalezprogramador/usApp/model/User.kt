package com.enriquegonzalezprogramador.usApp.model

import com.google.gson.annotations.SerializedName
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @SerializedName("username") var username: String,
    @SerializedName("edad") var edad: Int? = null,
    @SerializedName("sexo") var sexo: String? = null,
    @SerializedName("pais") var pais: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("email") var email: String
) {
    // Métodos de acceso

}

