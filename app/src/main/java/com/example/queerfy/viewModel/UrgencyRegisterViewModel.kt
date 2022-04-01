package com.example.queerfy.viewModel

import com.example.queerfy.model.UrgencyRegisterModel
import com.example.queerfy.utils.GenderIdentityEnum
import com.example.queerfy.utils.SexOrientationEnum

class UrgencyRegisterViewModel {
    var urgencyRegisterModel: UrgencyRegisterModel? = null
    var sexOrientationEnum = SexOrientationEnum.SELECT
    var genderIdentityEnum = GenderIdentityEnum.SELECT

    //TODO Criar função para enviar informações do urgencyRegisterModel para o banco
    fun putIntoBd() {
        this.urgencyRegisterModel?.let {
            // TODO Manda para banco

        }
    }
}