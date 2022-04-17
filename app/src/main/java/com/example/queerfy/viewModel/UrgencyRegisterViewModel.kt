package com.example.queerfy.viewModel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.queerfy.model.UrgencyRegisterModel
import com.example.queerfy.services.Api
import com.example.queerfy.utils.GenderIdentityEnum
import com.example.queerfy.utils.SexOrientationEnum
import com.example.queerfy.view.RegisterChooseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UrgencyRegisterViewModel {
    var urgencyRegisterModel: UrgencyRegisterModel? = null
    var sexOrientationEnum = SexOrientationEnum.SELECT
    var genderIdentityEnum = GenderIdentityEnum.SELECT

    //TODO Criar função para enviar informações do urgencyRegisterModel para o banco
    fun putIntoBd(urgencyRegister: UrgencyRegisterModel, context: Context) {
        this.urgencyRegisterModel?.let {
            val postUrgency = Api.create().registerUrgency(urgencyRegister)

            postUrgency.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    println(response)
                    if (response.isSuccessful) {
                        println("Criado com sucesso!")
                        Toast.makeText(context, "Usuario cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                        // Ir para tela de login
                        val loginActivity = Intent(context, RegisterChooseActivity::class.java)

                        context.startActivity(loginActivity)
                    }else {
                        Toast.makeText(context, "Erro ao se cadastrar, tente novamente!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // t.printStackTrace()
                    println(t.message)
                    Toast.makeText(context, "Erro na Api", Toast.LENGTH_SHORT).show()
                }

            })

        }
    }
}