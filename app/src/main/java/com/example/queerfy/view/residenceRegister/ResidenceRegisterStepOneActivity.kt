package com.example.queerfy.view.residenceRegister

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.queerfy.R
import com.example.queerfy.databinding.ActivityHouseRegisterStepOneBinding
import com.example.queerfy.view.showCustomToast

class ResidenceRegisterStepOneActivity: AppCompatActivity() {

    private lateinit var binding: ActivityHouseRegisterStepOneBinding
    lateinit var residenceRegister: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityHouseRegisterStepOneBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        residenceRegister = getSharedPreferences("residenceRegister", MODE_PRIVATE)
    }

    fun advancedStep(v: View) {

        val apartamentType = findViewById<RadioButton>(R.id.type_apartment)
        val houseType = findViewById<RadioButton>(R.id.type_house)
        val wholeSpace = findViewById<RadioButton>(R.id.space_whole)
        val roomSpace = findViewById<RadioButton>(R.id.space_room)
        val roomSharedSpace = findViewById<RadioButton>(R.id.space_room_shared)

        var propertyType = ""
        var spaceType = ""

        val residenceRegisterStepTwo = Intent(this, ResidenceRegisterStepTwoActivity::class.java)


        if (!apartamentType.isChecked && !houseType.isChecked) {
            Toast.makeText(this@ResidenceRegisterStepOneActivity, "Nenhum tipo selecionado!", Toast.LENGTH_SHORT).show()
            return
        }

        if (!wholeSpace.isChecked && !roomSpace.isChecked && !roomSharedSpace.isChecked) {
            Toast.makeText(this@ResidenceRegisterStepOneActivity, "Nenhum espaço selecionado!", Toast.LENGTH_SHORT).show()
            return
        }

        if (apartamentType.isChecked) {
            propertyType = "apartamento"
        }

        if (houseType.isChecked) {
            propertyType = "casa"
        }

        if (wholeSpace.isChecked) {
            spaceType = "lugar inteiro"
        }

        if (roomSpace.isChecked) {
            spaceType = "quarto inteiro"
        }

        if (roomSharedSpace.isChecked) {
            spaceType = "quarto compartilhado"
        }

        val editResidenceRegister = residenceRegister.edit()
        editResidenceRegister.putString("propertyType", propertyType)
        editResidenceRegister.putString("spaceType", spaceType)
        editResidenceRegister.commit()

        startActivity(residenceRegisterStepTwo)


    }

}