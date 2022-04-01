package com.example.queerfy.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.queerfy.databinding.ActivityRegisterChooseBinding


class RegisterChooseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterChooseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityRegisterChooseBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        setButtonListeners()
    }

    fun setButtonListeners() {
        binding.btnUrgency.setOnClickListener {
            val intent = Intent(this, UrgencyRegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnPlan.setOnClickListener {
            val intent = Intent(this, ToPlanRegisterActivity::class.java)
            startActivity(intent)
        }

    }
}