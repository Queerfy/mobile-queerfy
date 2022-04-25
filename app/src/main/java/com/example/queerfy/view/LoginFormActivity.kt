package com.example.queerfy.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.queerfy.databinding.ActivityLoginFormBinding
import com.example.queerfy.model.LoginUserModel
import com.example.queerfy.viewModel.LoginViewModel

class LoginFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginFormBinding
    private val loginUserViewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityLoginFormBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        setupListeners()
    }

    fun avancedRegisterChoose(v: View) {
        val registerChoose = Intent(this, RegisterChooseActivity::class.java)

        startActivity(registerChoose)
    }

    private fun setupListeners() {
        this.binding.btEntrar.setOnClickListener {
            setLoginUserModel()

            loginUserViewModel.loginUserModel?.let { it1 ->
                loginUserViewModel.putIntoBd(it1, this)
            }
        }
    }

    private fun setLoginUserModel() {
        loginUserViewModel.loginUserModel = LoginUserModel(
            email = this.binding.editEmail.text.toString(),
            password = this.binding.editSenha.text.toString()
        )
    }

}