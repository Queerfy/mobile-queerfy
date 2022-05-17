package com.example.queerfy.viewModel

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import com.example.queerfy.model.LoginUserModel
import com.example.queerfy.model.User
import com.example.queerfy.services.Api
import com.example.queerfy.view.ResidenceActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel {

    var loginUserModel: LoginUserModel? = null

    fun putIntoBd(modelUserLogin: LoginUserModel, context: Context, preferences: SharedPreferences) {
        this.loginUserModel?.let {
            val postLogin = Api.create().loginUser(modelUserLogin)

            val residenceActivity = Intent(context, ResidenceActivity::class.java)

            postLogin.enqueue(object: Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    println(response.code())
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Usuario logado com sucesso!", Toast.LENGTH_SHORT).show()

                        val editPreferences = preferences.edit()
                        editPreferences.putInt("idUser", response.body()?.id as Int)
                        editPreferences.commit()

                        context.startActivity(residenceActivity)

                    }else {
                        Toast.makeText(context, "Erro ao se logar, tente novamente!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    println(t.message)

                    Toast.makeText(context, "Erro na Api", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

}