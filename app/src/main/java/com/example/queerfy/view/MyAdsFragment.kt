package com.example.queerfy.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.queerfy.R
import com.example.queerfy.model.Property

class MyAdsFragment(
    val myAds: List<Property>,
): RecyclerView.Adapter<MyAdsFragment.AdsViewHolder>()  {

    override fun getItemCount() = myAds.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_my_ads, parent, false)

        return AdsViewHolder(view)
    }

    class AdsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {
        val myAd = myAds[position]
        val descAd = "${myAds[position].propertyType} - ${myAds[position].roomQuantity} quarto(s) disponivel"
        holder.itemView.findViewById<TextView>(R.id.desc_my_ad).text = descAd
    }

}