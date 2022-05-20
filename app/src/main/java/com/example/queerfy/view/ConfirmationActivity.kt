package com.example.queerfy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.queerfy.R

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

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

    }
}