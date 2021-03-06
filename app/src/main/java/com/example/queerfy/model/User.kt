package com.example.queerfy.model

import java.util.*
import kotlin.collections.HashSet

data class User (
    val id: Int?,
    val name: String?,
    val birthDate: Date?,
    val notifications: Boolean?,
    val rg: String?,
    val cpf: String?,
    val email: String?,
    val password: String?,
    val perfilImg: String?,
    val descUser: String?,
    val genre: String?,
    val likes: String?,
    val admin: Boolean?,
    val autenticated: Boolean?,
    val property: HashSet<Property>?,
    val favorite: HashSet<Favorite>?,
)