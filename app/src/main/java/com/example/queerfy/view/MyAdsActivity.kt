package com.example.queerfy.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.queerfy.databinding.ActivityMyAdsBinding

class MyAdsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyAdsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMyAdsBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
    }
}