package com.example.queerfy.model

data class UpdateUserModel(
    val name: String?,
    val cpf: String?,
    val email: String?,
    val password: String?,
    val descUser: String? // Genero do usuario pois está invertido com o descUser
)
