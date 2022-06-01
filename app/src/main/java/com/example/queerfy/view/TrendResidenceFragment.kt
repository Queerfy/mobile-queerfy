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

class TrendResidenceFragment (
        val properties: List<Property>
        ): RecyclerView.Adapter<TrendResidenceFragment.TrendResidenceViewHolder> (){

    override fun getItemCount() = properties.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendResidenceViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_trend_residence, parent, false)

        return TrendResidenceViewHolder(view)
    }

    class TrendResidenceViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    fun getPropertyType(typeProperty: String?): String {
        if(typeProperty == "casa") {
            return "Casa"
        }else {
            return "Apartamento"
        }
    }

    override fun onBindViewHolder(holder: TrendResidenceViewHolder, position: Int) {
        val property = properties[position]

        val descResidence = "${getPropertyType(property.propertyType)} - ${property.roomQuantity} quarto(s) disponivel"
        val priceProperty = "R$ ${property.dailyPrice?.toInt()} / noite"

        holder.itemView.findViewById<TextView>(R.id.title_residence_trend).text = descResidence
        holder.itemView.findViewById<TextView>(R.id.desc_residence_trend).text = priceProperty

        val residencePage = Intent(holder.itemView.context, ResidenceActivity::class.java)

        holder.itemView.findViewById<LinearLayout>(R.id.residence_trend_container).setOnClickListener {
            residencePage.putExtra("idHouse", property.id)

            holder.itemView.context.startActivity(residencePage)
        }
    }

}