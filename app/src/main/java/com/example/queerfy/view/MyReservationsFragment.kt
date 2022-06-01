package com.example.queerfy.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.queerfy.R
import com.example.queerfy.model.Property
import com.example.queerfy.model.User
import com.example.queerfy.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    fun getPropertyType(typeProperty: String?): String {
        if(typeProperty == "casa") {
            return "Casa"
        }else {
            return "Apartamento"
        }
    }

    override fun onBindViewHolder(holder: ReservationsAdsViewHolder, position: Int) {
        val myReservation = myReservations[position]

        val getProperty = Api.create().getProperty(myReservation.id as Int)

        val residencePage = Intent(holder.itemView.context, ResidenceActivity::class.java)

        getProperty.enqueue(object: Callback<Property> {
            override fun onResponse(call: Call<Property>, response: Response<Property>) {

                if(response.isSuccessful) {

                    val descResidence = "${getPropertyType(response.body()?.propertyType)} - ${response.body()?.roomQuantity} quarto(s) disponivel"

                    val idOwner = response.body()?.idUser as Int

                    holder.itemView.findViewById<TextView>(R.id.desc_reservation).text = descResidence

                    holder.itemView.findViewById<AppCompatButton>(R.id.btn_residence_view).setOnClickListener{
                        residencePage.putExtra("idHouse", response.body()?.id)

                        holder.itemView.context.startActivity(residencePage)
                    }

                    val getOwner = Api.create().getUser(idOwner)

                    getOwner.enqueue(object: Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            if (response.isSuccessful) {

                                val nameOwner = response.body()?.name

                                holder.itemView.findViewById<TextView>(R.id.owner_reservation).text = "Locador(a): ${nameOwner}"
                            }
                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {
                            Toast.makeText(holder.itemView.context, "Erro ao caregar as informações do locador!", Toast.LENGTH_SHORT).show()
                        }

                    })

                }

            }

            override fun onFailure(call: Call<Property>, t: Throwable) {
                Toast.makeText(holder.itemView.context, "Erro ao caregar as informações!", Toast.LENGTH_SHORT).show()
            }

        })
    }

}