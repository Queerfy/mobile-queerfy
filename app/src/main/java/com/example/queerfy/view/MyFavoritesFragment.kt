package com.example.queerfy.view


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.queerfy.R
import com.example.queerfy.model.Favorite
import com.example.queerfy.model.Property
import com.example.queerfy.model.User
import com.example.queerfy.services.Api
import retrofit2.Call
import retrofit2.Callback
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
        val myFavorite = myFavorites[position]

        val getProperty = Api.create().getProperty(myFavorite.propertyId as Int)

        getProperty.enqueue(object : retrofit2.Callback<Property> {
            override fun onResponse(
                call: Call<Property>,
                response: Response<Property>
            ) {

                if (response.isSuccessful) {
                    val descAd = "${response.body()?.propertyType} - ${response.body()?.roomQuantity} quarto(s) disponivel"
                    holder.itemView.findViewById<TextView>(R.id.property_name).text = descAd

                    val idOwner = response.body()?.idUser as Int

                    val residencePage = Intent(holder.itemView.context, ResidenceActivity::class.java)

                    holder.itemView.findViewById<ImageView>(R.id.icon_favorite).setOnClickListener{

                        val deleteFavorite = Api.create().deleteFavorite(myFavorite.id as Int)

                        deleteFavorite.enqueue(object: Callback<Void>{
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {

                                holder.itemView.findViewById<LinearLayout>(R.id.ll_myfavorites).visibility = View.GONE

                                Toast.makeText(holder.itemView.context, "Deletado com sucesso!", Toast.LENGTH_SHORT).show()
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                Toast.makeText(holder.itemView.context, "Erro ao deletar!", Toast.LENGTH_SHORT).show()
                            }

                        })

                    }

                    holder.itemView.findViewById<AppCompatButton>(R.id.btn_view_residence).setOnClickListener {
                        residencePage.putExtra("idHouse", myFavorite.id)

                        holder.itemView.context.startActivity(residencePage)
                    }

                    val getOwner = Api.create().getUser(idOwner)

                    getOwner.enqueue(object: Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            if (response.isSuccessful) {

                                val nameOwner = response.body()?.name

                                holder.itemView.findViewById<TextView>(R.id.user_name).text = "Locador(a): ${nameOwner}"
                            }
                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {
                            Toast.makeText(holder.itemView.context, "Erro ao caregar as informações do locador!", Toast.LENGTH_SHORT).show()
                        }

                    })

                }

            }

            override fun onFailure(call: Call<Property>, t: Throwable) {
                println("Erro ao Carregar as informações!")
            }

        })
    }

}