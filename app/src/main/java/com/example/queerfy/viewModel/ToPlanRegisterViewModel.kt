package com.example.queerfy.viewModel

import com.example.queerfy.model.ToPlanRegisterModel
import com.example.queerfy.utils.GenderIdentityEnum
import com.example.queerfy.utils.SexOrientationEnum

class ToPlanRegisterViewModel {
    var toPlanRegisterModel: ToPlanRegisterModel? = null
    var sexOrientationEnum = SexOrientationEnum.SELECT
    var genderIdentityEnum = GenderIdentityEnum.SELECT

    //TODO Criar função para enviar informações do urgencyRegisterModel para o banco
    fun putIntoBd() {
        this.toPlanRegisterModel?.let {
            // TODO Manda para banco
        }
    }
}