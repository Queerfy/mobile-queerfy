package com.example.queerfy.view.residenceRegister

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.queerfy.R
import com.example.queerfy.databinding.ActivityHouseRegisterStepFourBinding

class ResidenceRegisterStepFourActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHouseRegisterStepFourBinding
    lateinit var residenceRegister: SharedPreferences

    var haveWifi = false
    var haveChicken = false
    var haveGarage = false
    var haveAnimals = false
    var haveSuite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityHouseRegisterStepFourBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        residenceRegister = getSharedPreferences("residenceRegister", MODE_PRIVATE)
    }

    fun handleClickedButton(v: View) {
        if (v is CheckBox) {
            val checked = v.isChecked

            when(v.getId()) {
                R.id.have_wifi ->
                    if(checked) {
                        haveWifi = checked
                    }

                R.id.have_chicken ->
                    if(checked) {
                        haveChicken = checked
                    }

                R.id.have_garage ->
                    if(checked) {
                        haveGarage = checked
                    }

                R.id.have_animals ->
                    if(checked) {
                        haveAnimals = checked
                    }

                R.id.have_suite ->
                    if(checked) {
                        haveSuite = checked
                    }
            }
        }
    }


    fun advancedStepFive(v: View) {

        val residenceRegisterStepFive = Intent(this, ResidenceRegisterStepFiveActivity::class.java)

        if (!haveWifi && !haveChicken && !haveGarage && !haveAnimals && !haveSuite) {
            Toast.makeText(this@ResidenceRegisterStepFourActivity, "Selecione uma opção!", Toast.LENGTH_SHORT).show()
            return
        }

        val editResidenceRegister = residenceRegister.edit()
        editResidenceRegister.putBoolean("haveWifi",haveWifi)
        editResidenceRegister.putBoolean("haveChicken",haveChicken)
        editResidenceRegister.putBoolean("haveGarage",haveGarage)
        editResidenceRegister.putBoolean("haveAnimals",haveAnimals)
        editResidenceRegister.putBoolean("haveSuite",haveSuite)
        editResidenceRegister.commit()

        startActivity(residenceRegisterStepFive)

    }

}