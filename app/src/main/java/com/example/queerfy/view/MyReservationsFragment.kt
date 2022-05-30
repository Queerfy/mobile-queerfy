package com.example.queerfy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.queerfy.R
import com.example.queerfy.model.Property

class MyReservationsFragment(
    val myReservations: List<Property>
) : RecyclerView.Adapter<MyReservationsFragment.ReservationsAdsViewHolder>() {

    override fun getItemCount() = myReservations.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationsAdsViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_my_reservations, parent, false)

        return ReservationsAdsViewHolder(view)
    }

    class ReservationsAdsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onBindViewHolder(holder: ReservationsAdsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}