package com.example.queerfy.view

import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.queerfy.R
import com.example.queerfy.model.NewLeaseModel
import com.example.queerfy.viewModel.ConfirmReservartionViewModel
import java.time.LocalDate

class ConfirmationActivity : AppCompatActivity() {

    lateinit var userPreferences: SharedPreferences
    private val confirmReservartionViewModel = ConfirmReservartionViewModel()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        userPreferences = getSharedPreferences("userPreferences", MODE_PRIVATE)

        val idUser = userPreferences.getInt("idUser", 0)

        val idProperty = intent.getStringExtra("idProperty")
        val residenceTitle = intent.getStringExtra("residenceTitle")
        val uf = intent.getStringExtra("uf")
        val residenceDetails = intent.getStringExtra("residenteDetails")
        val datachekin = intent.getStringExtra("datachekin")
        val datachekout = intent.getStringExtra("datachekout")
        val dailys = intent.getIntExtra("dailys", 0)
        val dailyPrice = intent.getDoubleExtra("dailyPrice", 0.0)
        val total = intent.getDoubleExtra("total", 0.0)

        findViewById<TextView>(R.id.residence_title).text = residenceTitle
        findViewById<TextView>(R.id.uf_residence).text = uf
        findViewById<TextView>(R.id.details_residence).text = residenceDetails
        findViewById<TextView>(R.id.checkin_date).text = datachekin
        findViewById<TextView>(R.id.checkout_date).text = datachekout
        findViewById<TextView>(R.id.confirmation_total_nights).text = "${dailys}"
        findViewById<TextView>(R.id.confirmation_total_nights_value).text = "R$ ${"%,.2f".format(dailyPrice)}"
        findViewById<TextView>(R.id.confirmation_total_value).text = "R$ ${"%,.2f".format(total)}"

        confirmReservartionViewModel.newLeaseModel = NewLeaseModel(
            idProperty = idProperty?.toInt(),
            idUser = idUser,
            checkIn = datachekin,
            checkOut = datachekout,
            totalValue = total
        )

        println(datachekin)
        println(datachekout)
        println(total)


    }

    fun createLease(v: View) {
        confirmReservartionViewModel.newLeaseModel?.let { it1 ->
            confirmReservartionViewModel.putIntoBd(it1, this)
        }
    }
}