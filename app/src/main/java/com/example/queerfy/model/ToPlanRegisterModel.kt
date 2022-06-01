package com.example.queerfy.model

import com.example.queerfy.utils.GenderIdentityEnum
import com.example.queerfy.utils.SexOrientationEnum
import java.util.*

data class ToPlanRegisterModel(
    val name: String,
    val birthDate: String,
    val sexOrientation: SexOrientationEnum,
    val genderIdentity: String,
    val rg: String,
    val cpf: String,
    val email: String,
    val phone: String,
    val password: String
)
