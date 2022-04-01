package com.example.queerfy.model

import com.example.queerfy.utils.GenderIdentityEnum
import com.example.queerfy.utils.SexOrientationEnum
import java.util.*

data class ToPlanRegisterModel(
    val name: String,
    val sinceDate: Date,
    val sexOrientation: SexOrientationEnum,
    val genderIdentity: GenderIdentityEnum,
    val rg: String,
    val cpf: String,
    val email: String,
    val phone: String,
    val password: String
)
