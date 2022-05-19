package com.example.queerfy.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.queerfy.R
import com.example.queerfy.databinding.AccountActivityBinding
import com.example.queerfy.model.User
import com.example.queerfy.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountActivity: AppCompatActivity() {

    private lateinit var binding: AccountActivityBinding
    lateinit var userPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = AccountActivityBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        userPreferences = getSharedPreferences("userPreferences", MODE_PRIVATE)

        val idUser = userPreferences.getInt("idUser", 0)

        val getUser = Api.create().getUser(idUser)

        getUser.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {

                if (response.isSuccessful) {

                    val name = response.body()?.name
                    val cpf = response.body()?.cpf
                    val email = response.body()?.email
                    val password = response.body()?.password

                    binding.edtName.hint = name
                    binding.edtCpf.hint = cpf
                    binding.edtEmail.hint = email
                    binding.edtPassword.hint = password
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                println(t.message)
                Toast(this@AccountActivity).showCustomToast("Erro ao carregar as informações!", this@AccountActivity)
            }

        })

    }
}