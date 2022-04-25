package com.example.queerfy.viewModel

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.example.queerfy.model.LoginUserModel
import com.example.queerfy.model.User
import com.example.queerfy.services.Api
import com.example.queerfy.view.showCustomToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel {

    var loginUserModel: LoginUserModel? = null

    fun putIntoBd(modelUserLogin: LoginUserModel, context: Context) {
        this.loginUserModel?.let {
            val postLogin = Api.create().loginUser(modelUserLogin)

            postLogin.enqueue(object: Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Toast(context).showCustomToast("Usuario logado com sucesso!", context as Activity)
                    }else {
                        Toast(context).showCustomToast("Email ou Senha Incorreto!", context as Activity)
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    println(t.message)

                    Toast(context).showCustomToast("Erro ao se logar!", context as Activity)
                }

            })
        }
    }

}