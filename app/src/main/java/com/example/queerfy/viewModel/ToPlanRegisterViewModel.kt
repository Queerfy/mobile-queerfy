package com.example.queerfy.viewModel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.queerfy.model.ToPlanRegisterModel
import com.example.queerfy.services.Api
import com.example.queerfy.utils.GenderIdentityEnum
import com.example.queerfy.utils.SexOrientationEnum
import com.example.queerfy.view.LoginFormActivity
import com.example.queerfy.view.RegisterChooseActivity
import com.example.queerfy.view.showCustomToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ToPlanRegisterViewModel {
    var toPlanRegisterModel: ToPlanRegisterModel? = null
    var sexOrientationEnum = SexOrientationEnum.SELECT
    var genderIdentityEnum = GenderIdentityEnum.SELECT

    fun putIntoBd(toPlanRegisterModel: ToPlanRegisterModel, context: Context) {
        this.toPlanRegisterModel?.let {
            val postToPlan = Api.create().registerToPlan(toPlanRegisterModel)

            postToPlan.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast(context).showCustomToast(
                            "Usuario cadastrado com sucesso!",
                            context as Activity
                        )
                        // Ir para tela de login
                        val loginActivity = Intent(context, LoginFormActivity::class.java)

                        context.startActivity(loginActivity)
                    } else {
                        Toast(context).showCustomToast(
                            "Erro ao cadastrar!",
                            context as Activity
                        )
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // t.printStackTrace()
                    println(t.message)
                    Toast(context).showCustomToast(
                        "Erro ao cadastrar!",
                        context as Activity
                    )
                }

            })
        }
    }
}