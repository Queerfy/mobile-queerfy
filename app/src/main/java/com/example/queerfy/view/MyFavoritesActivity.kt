package com.example.queerfy.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.queerfy.R
import com.example.queerfy.databinding.ActivivityMyFavoritesBinding
import com.example.queerfy.model.Favorite
import com.example.queerfy.model.Property
import com.example.queerfy.model.User
import com.example.queerfy.services.Api
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_my_favorites.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MyFavoritesActivity: AppCompatActivity() {
    private lateinit var binding: ActivivityMyFavoritesBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivivityMyFavoritesBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        recyclerView = findViewById(R.id.favoritesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)

        getMyFavorites()
    }

    fun getMyFavorites() {
        val preferences = getSharedPreferences("userPreferences", MODE_PRIVATE)

        val idUser = preferences.getInt("idUser", 0)

        val favoritesGet = Api.create().getUser(idUser)

        favoritesGet.enqueue(object: retrofit2.Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful) {

                    var favoriteList = mutableListOf<Favorite>()

                    var favorites = response.body()?.favorite

                    if(favorites?.isEmpty() == false) {

                        favorites.forEach { favorite ->
                            favoriteList.add(favorite)
                        }

                        recyclerView.adapter = MyFavoritesFragment(favoriteList)

                    }

                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast(this@MyFavoritesActivity).showCustomToast("Ao carregar informaçãoe do usuario!", this@MyFavoritesActivity)
            }

        })
    }

}