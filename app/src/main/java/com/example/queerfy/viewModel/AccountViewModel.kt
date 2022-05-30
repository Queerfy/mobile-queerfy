package com.example.queerfy.viewModel

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.example.queerfy.model.UpdateUserModel
import com.example.queerfy.model.User
import com.example.queerfy.services.Api
import com.example.queerfy.view.showCustomToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountViewModel {
    var updateUserModel: UpdateUserModel? = null

    fun putIntoBd(updateUser: UpdateUserModel, context: Context, preferences: SharedPreferences) {
        this.updateUserModel?.let {

            val idUser = preferences.getInt("idUser", 0)

            val updateUser = Api.create().updateUser(idUser, updateUser)

            updateUser.enqueue(object: Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(context, "Erro ao atualizar!", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}