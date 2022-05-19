package com.example.queerfy.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.queerfy.R
import com.example.queerfy.model.Favorite
import com.example.queerfy.model.Property
import com.example.queerfy.services.Api
import retrofit2.Call
import retrofit2.Response


class MyFavoritesFragment(
    val myFavorites: List<Favorite>,
) : RecyclerView.Adapter<MyFavoritesFragment.FavoritesViewHolder>() {

    override fun getItemCount() = myFavorites.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_my_favorites, parent, false)

        return FavoritesViewHolder(view)
    }

    class FavoritesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val myAd = myFavorites[position]

        val getProperty = Api.create().getProperty(myAd.propertyId as Int)

        getProperty.enqueue(object : retrofit2.Callback<Property> {
            override fun onResponse(
                call: Call<Property>,
                response: Response<Property>
            ) {

                if (response.isSuccessful) {
                    val descAd = "${response.body()?.propertyType} - ${response.body()?.roomQuantity} quarto(s) disponivel"
                    holder.itemView.findViewById<TextView>(R.id.property_name).text = descAd
                }

            }

            override fun onFailure(call: Call<Property>, t: Throwable) {
                println("Erro ao Carregar as informações")
            }

        })
    }

}