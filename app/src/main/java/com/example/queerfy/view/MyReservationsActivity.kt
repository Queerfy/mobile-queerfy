package com.example.queerfy.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.queerfy.R
import com.example.queerfy.databinding.ActivityMyReservationsBinding

class MyReservationsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyReservationsBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMyReservationsBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        recyclerView = findViewById(R.id.reservationsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)

    }

    fun listMyReservations() {
        val preferences = getSharedPreferences("userPreferences", MODE_PRIVATE)

        val idUser = preferences.getInt("idUser", 0)

    }

}