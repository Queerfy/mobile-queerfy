package com.example.queerfy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.queerfy.R

class ResidenceListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_residence_list)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.residence_list_trend_one, TrendResidenceFragment::class.java, null)
        transaction.commit()

        val transaction2 = supportFragmentManager.beginTransaction()
        transaction2.add(R.id.residence_list_fragment, ResidenceFragment::class.java, null)
        transaction2.commit()
    }
}