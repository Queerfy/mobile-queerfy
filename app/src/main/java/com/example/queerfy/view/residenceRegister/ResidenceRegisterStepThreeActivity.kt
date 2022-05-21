package com.example.queerfy.view.residenceRegister

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.queerfy.R
import com.example.queerfy.databinding.ActivityHouseRegisterStepThreeBinding

class ResidenceRegisterStepThreeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHouseRegisterStepThreeBinding
    lateinit var residenceRegister: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityHouseRegisterStepThreeBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        residenceRegister = getSharedPreferences("residenceRegister", MODE_PRIVATE)
    }

    fun advancedStepFour(v: View) {

        val guestsQuantity = findViewById<EditText>(R.id.edtGuests).text.toString()
        val roomQuantity = findViewById<EditText>(R.id.edtBedrooms).text.toString()
        val bedsQuantity = findViewById<EditText>(R.id.edtBeds).text.toString()
        val bathroomQuantity = findViewById<EditText>(R.id.edtBathrooms).text.toString()

        val residenceRegisterStepFour = Intent(this, ResidenceRegisterStepFourActivity::class.java)

        if (guestsQuantity.isEmpty()) {
            Toast.makeText(this@ResidenceRegisterStepThreeActivity, "Quantidade de hospedes invalida!", Toast.LENGTH_SHORT).show()
            return
        }

        if (roomQuantity.isEmpty()) {
            Toast.makeText(this@ResidenceRegisterStepThreeActivity, "Quantidade de quartos invalida!", Toast.LENGTH_SHORT).show()
            return
        }

        if (bedsQuantity.isEmpty()) {
            Toast.makeText(this@ResidenceRegisterStepThreeActivity, "Quantidade de camas invalida!", Toast.LENGTH_SHORT).show()
            return
        }

        if (bathroomQuantity.isEmpty()) {
            Toast.makeText(this@ResidenceRegisterStepThreeActivity, "Quantidade de banheiros invalida!", Toast.LENGTH_SHORT).show()
            return
        }

        val editResidenceRegister = residenceRegister.edit()
        editResidenceRegister.putString("guestsQuantity", guestsQuantity)
        editResidenceRegister.putString("roomQuantity", roomQuantity)
        editResidenceRegister.putString("bedsQuantity", bedsQuantity)
        editResidenceRegister.putString("bathroomQuantity", bathroomQuantity)
        editResidenceRegister.commit()

        startActivity(residenceRegisterStepFour)

    }

}