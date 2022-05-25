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
import com.example.queerfy.model.UpdateUserModel
import com.example.queerfy.model.User
import com.example.queerfy.services.Api
import com.example.queerfy.utils.GenderIdentityEnum
import com.example.queerfy.viewModel.AccountViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountActivity: AppCompatActivity() {

    private lateinit var binding: AccountActivityBinding
    lateinit var userPreferences: SharedPreferences
    private val accountViewModel = AccountViewModel()

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

                    if (genre.toString().equals("Homem")) {
                        binding.spnGenderIdentity.setSelection(1)
                    }

                    if (genre.toString().equals("Mulher")) {
                        binding.spnGenderIdentity.setSelection(2)
                    }

                    if (genre.toString().equals("Não-binário")) {
                        binding.spnGenderIdentity.setSelection(3)
                    }

                    if (genre.toString().equals("Não informar")) {
                        binding.spnGenderIdentity.setSelection(3)
                    }

                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                println(t.message)
                Toast(this@AccountActivity).showCustomToast("Erro ao carregar as informações!", this@AccountActivity)
            }

        })

        setupGenderIdentitySpinner()

        this.binding.btnFinish.setOnClickListener {
            updateAccount()
        }

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

    private fun updateAccount() {
        val name = binding.edtName.text.toString()
        val cpf = binding.edtCpf.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        val genre =  binding.spnGenderIdentity.selectedItem.toString() //Invertido como descUser

        if (name.isEmpty()) {
            Toast.makeText(this, "Nome Invalido!", Toast.LENGTH_SHORT).show()
            return
        }

        if(cpf.isEmpty()) {
            Toast.makeText(this, "Cpf Invalido!", Toast.LENGTH_SHORT).show()
            return
        }

        if(email.isEmpty()) {
            Toast.makeText(this, "Email Invalido!", Toast.LENGTH_SHORT).show()
            return
        }

        if(password.isEmpty()) {
            Toast.makeText(this, "Senha Invalida!", Toast.LENGTH_SHORT).show()
            return
        }

        accountViewModel.updateUserModel = UpdateUserModel(
            name = name,
            cpf = cpf,
            email = email,
            password = password,
            descUser = genre
        )

        accountViewModel.updateUserModel?.let{it1 ->
            accountViewModel.putIntoBd(
                it1, this, userPreferences
            )
        }

    }

}