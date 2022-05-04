package com.example.queerfy.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.queerfy.databinding.ActivivityMyFavoritesBinding

class MyFavoritesActivity: AppCompatActivity() {
    private lateinit var binding: ActivivityMyFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivivityMyFavoritesBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
    }

}