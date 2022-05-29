package com.example.queerfy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onBindViewHolder(holder: TrendResidenceViewHolder, position: Int) {

    }

}