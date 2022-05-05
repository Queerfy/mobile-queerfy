package com.example.queerfy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.queerfy.R

class ResidenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_residence)

        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.fragment_residence_footer, FragmentFooter::class.java, null)
        transaction.commit()
    }
}