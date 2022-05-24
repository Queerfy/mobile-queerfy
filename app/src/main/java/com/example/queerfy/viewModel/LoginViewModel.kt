package com.example.queerfy.viewModel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import com.example.queerfy.model.LoginUserModel
import com.example.queerfy.model.User
import com.example.queerfy.services.Api
import com.example.queerfy.view.*
import com.example.queerfy.view.residenceRegister.ResidenceRegisterStepOneActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel {

    var loginUserModel: LoginUserModel? = null

    fun putIntoBd(modelUserLogin: LoginUserModel, context: Context, preferences: SharedPreferences) {
        this.loginUserModel?.let {
            val postLogin = Api.create().loginUser(modelUserLogin)

            val residenceActivity = Intent(context, ResidenceActivity::class.java)
            val residenceRegister = Intent(context, ResidenceRegisterStepOneActivity::class.java)
            val accountActivity = Intent(context, AccountActivity::class.java)
            val homePage = Intent(context, NavigationDrawerActivity::class.java)

            postLogin.enqueue(object: Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Toast(context).showCustomToast("Usuario logado com sucesso!", context as Activity)

                        val editPreferences = preferences.edit()
                        editPreferences.putInt("idUser", response.body()?.id as Int)
                        editPreferences.commit()

                        context.startActivity(accountActivity)

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