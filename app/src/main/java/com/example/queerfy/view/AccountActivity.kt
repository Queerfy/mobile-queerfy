package com.example.queerfy.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.queerfy.R
import com.example.queerfy.databinding.AccountActivityBinding
import com.example.queerfy.model.User
import com.example.queerfy.services.Api
import com.example.queerfy.utils.GenderIdentityEnum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountActivity: AppCompatActivity() {

    private lateinit var binding: AccountActivityBinding
    lateinit var userPreferences: SharedPreferences

    var genderIdentityEnum = GenderIdentityEnum.SELECT

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
                    val genre = response.body()?.genre

                    binding.edtName.setText(name)
                    binding.edtCpf.setText(cpf)
                    binding.edtEmail.setText(email)
                    binding.edtPassword.setText(password)
                    // binding.spnGenderIdentity.setSelection(1)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                println(t.message)
                Toast(this@AccountActivity).showCustomToast("Erro ao carregar as informações!", this@AccountActivity)
            }

        })

        setupGenderIdentitySpinner()

        println(genderIdentityEnum.name)

    }

    private fun setupGenderIdentitySpinner() {
        val options = GenderIdentityEnum.toList()
        this.binding.spnGenderIdentity.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            options
        )

        this.binding.spnGenderIdentity.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    genderIdentityEnum = GenderIdentityEnum.fromId(position) ?: GenderIdentityEnum.SELECT
                }

            }
    }

    private fun updateAccount(v: View) {
        // Colocar a função que vai enviar todos os dados aqui
    }

}