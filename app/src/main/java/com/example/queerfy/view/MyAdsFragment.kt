package com.example.queerfy.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.queerfy.R
import com.example.queerfy.model.Property
import com.example.queerfy.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val descAd = "${myAd.propertyType} - ${myAd.roomQuantity} quarto(s) disponivel"
        holder.itemView.findViewById<TextView>(R.id.desc_my_ad).text = descAd

        val residencePage = Intent(holder.itemView.context, ResidenceActivity::class.java)

        holder.itemView.findViewById<ImageView>(R.id.trash_item).setOnClickListener {
            val deleteProperty = Api.create().deleteProperty(myAd.id as Int)

            deleteProperty.enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {

                    holder.itemView.findViewById<LinearLayout>(R.id.ll_myads).visibility = View.GONE

                    Toast.makeText(holder.itemView.context, "Deletado com sucesso!", Toast.LENGTH_SHORT).show()

                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(holder.itemView.context, "Erro ao deletar!", Toast.LENGTH_SHORT).show()
                }

            })

        }

        holder.itemView.findViewById<LinearLayout>(R.id.btn_view_residence).setOnClickListener {
            residencePage.putExtra("idHouse", myAd.id)

            holder.itemView.context.startActivity(residencePage)
        }

    }

}