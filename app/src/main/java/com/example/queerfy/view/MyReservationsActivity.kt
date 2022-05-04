package com.example.queerfy.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.queerfy.databinding.ActivityMyReservationsBinding

class MyReservationsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMyReservationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMyReservationsBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
    }

}