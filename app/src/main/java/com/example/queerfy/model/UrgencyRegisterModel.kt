package com.example.queerfy.model

import com.example.queerfy.utils.GenderIdentityEnum
import com.example.queerfy.utils.SexOrientationEnum

data class UrgencyRegisterModel(
    val name: String,
    val sexOrientation: SexOrientationEnum,
    val genre: String,
    val cpf: String,
    val email: String,
    val phone: String,
    val password: String
)
