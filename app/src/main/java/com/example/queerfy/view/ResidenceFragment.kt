package com.example.queerfy.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.queerfy.R
import com.example.queerfy.model.Property

class ResidenceFragment(
    val properties: List<Property>
) : RecyclerView.Adapter<ResidenceFragment.ResidenceViewHolder>() {

    override fun getItemCount() = properties.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResidenceViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_residence, parent, false)

        return ResidenceViewHolder(view)
    }

    class ResidenceViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onBindViewHolder(holder: ResidenceViewHolder, position: Int) {
        val property = properties[position]

        val descResidence = "${property.propertyType} - ${property.roomQuantity} quarto(s) disponivel"
        val priceProperty = "R$ ${property.dailyPrice?.toInt()} / noite"

        holder.itemView.findViewById<TextView>(R.id.desc_residence).text = descResidence
        holder.itemView.findViewById<TextView>(R.id.price_residence).text = priceProperty

        val residencePage = Intent(holder.itemView.context, ResidenceActivity::class.java)

        holder.itemView.findViewById<LinearLayout>(R.id.residence_container).setOnClickListener {
            residencePage.putExtra("idHouse", property.id)

            holder.itemView.context.startActivity(residencePage)
        }


    }
}