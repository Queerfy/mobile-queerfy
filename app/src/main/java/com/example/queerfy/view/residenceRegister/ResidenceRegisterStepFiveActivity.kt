package com.example.queerfy.view.residenceRegister

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.queerfy.R
import com.example.queerfy.databinding.ActivityHouseRegisterStepFiveBinding

class ResidenceRegisterStepFiveActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHouseRegisterStepFiveBinding
    lateinit var residenceRegister: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityHouseRegisterStepFiveBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        residenceRegister = getSharedPreferences("residenceRegister", MODE_PRIVATE)
    }

    fun advancedStepSix(v: View) {
        val titleResidence = findViewById<EditText>(R.id.edtCreateTitle).text.toString()
        val describeResidence = findViewById<EditText>(R.id.edtCreateDescribe).text.toString()
        val dailyPrice = findViewById<EditText>(R.id.edtValueDaily).text.toString()

        val residenceRegisterStepSix = Intent(this, ResidenceRegisterStepSixActivity::class.java)

        if (titleResidence.isEmpty()) {
            Toast.makeText(this@ResidenceRegisterStepFiveActivity, "Titulo invalido!", Toast.LENGTH_SHORT).show()
            return
        }

        if (describeResidence.isEmpty()) {
            Toast.makeText(this@ResidenceRegisterStepFiveActivity, "Descrição invalida!", Toast.LENGTH_SHORT).show()
            return
        }

        if (dailyPrice.isEmpty()) {
            Toast.makeText(this@ResidenceRegisterStepFiveActivity, "Valor de Diaria invalido!", Toast.LENGTH_SHORT).show()
            return
        }

        val editResidenceRegister = residenceRegister.edit()
        editResidenceRegister.putString("titleResidence",titleResidence)
        editResidenceRegister.putString("describeResidence",describeResidence)
        editResidenceRegister.putFloat("dailyPrice",dailyPrice.toFloat())
        editResidenceRegister.commit()

        startActivity(residenceRegisterStepSix)

    }

}