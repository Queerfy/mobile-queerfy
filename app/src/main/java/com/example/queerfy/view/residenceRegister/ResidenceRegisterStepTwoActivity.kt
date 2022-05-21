package com.example.queerfy.view.residenceRegister

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.queerfy.R
import com.example.queerfy.databinding.ActivityHouseRegisterStepTwoBinding

class ResidenceRegisterStepTwoActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHouseRegisterStepTwoBinding
    lateinit var residenceRegister: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityHouseRegisterStepTwoBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        residenceRegister = getSharedPreferences("residenceRegister", MODE_PRIVATE)
    }

    fun advancedThreeStep(v: View) {
        val street = findViewById<EditText>(R.id.edtStreet).text.toString()
        val city = findViewById<EditText>(R.id.edtCity).text.toString()
        val cep = findViewById<EditText>(R.id.edtCep).text.toString()
        val district = findViewById<EditText>(R.id.edtDistrict).text.toString()
        val complement = findViewById<EditText>(R.id.edtComplement).text.toString()
        val referencePoint = findViewById<EditText>(R.id.edtReferencePoints).text.toString()

        val residenceStepTwo = Intent(this, ResidenceRegisterStepThreeActivity::class.java)

        if (street.isEmpty()) {
            Toast.makeText(this@ResidenceRegisterStepTwoActivity, "Endereço Invalido!", Toast.LENGTH_SHORT).show()
            return
        }

        if (city.isEmpty()) {
            Toast.makeText(this@ResidenceRegisterStepTwoActivity, "Cidade Invalida!", Toast.LENGTH_SHORT).show()
            return
        }

        if (cep.isEmpty()) {
            Toast.makeText(this@ResidenceRegisterStepTwoActivity, "Cep Invalido!", Toast.LENGTH_SHORT).show()
            return
        }

        if (district.isEmpty()) {
            Toast.makeText(this@ResidenceRegisterStepTwoActivity, "Bairro Invalido!", Toast.LENGTH_SHORT).show()
            return
        }

        if (complement.isEmpty()) {
            Toast.makeText(this@ResidenceRegisterStepTwoActivity, "Complemento Invalido!", Toast.LENGTH_SHORT).show()
            return
        }

        if (referencePoint.isEmpty()) {
            Toast.makeText(this@ResidenceRegisterStepTwoActivity, "Ponto de Referencia Invalido!", Toast.LENGTH_SHORT).show()
            return
        }

        val editResidenceRegister = residenceRegister.edit()
        editResidenceRegister.putString("street", street)
        editResidenceRegister.putString("city", city)
        editResidenceRegister.putString("cep", cep)
        editResidenceRegister.putString("district", district)
        editResidenceRegister.putString("complement", complement)
        editResidenceRegister.putString("referencePoint", referencePoint)
        editResidenceRegister.commit()

        startActivity(residenceStepTwo)

    }

}