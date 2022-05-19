package com.example.queerfy.view

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.queerfy.R
import com.example.queerfy.databinding.ActivityMyAdsBinding
import com.example.queerfy.model.Property
import com.example.queerfy.model.User
import com.example.queerfy.services.Api
import retrofit2.Call
import retrofit2.Response

class MyAdsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyAdsBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMyAdsBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        recyclerView = findViewById(R.id.adsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)

        listMyAds()
    }

    fun listMyAds() {
        val idUser = intent.getIntExtra("id", 0)

        val getProperties = Api.create().getProperties()

        getProperties.enqueue(object: retrofit2.Callback<List<Property>> {
            override fun onResponse(
                call: Call<List<Property>>,
                response: Response<List<Property>>
            ) {
                var propertyList = mutableListOf<Property>()

                if (response.isSuccessful) {
                    response.body()?.forEach { item ->
                        if(item.idUser == idUser) {
                            propertyList.add(item)
                        }
                    }

                    recyclerView.adapter = MyAdsFragment(
                        propertyList
                    )

                }
            }

            override fun onFailure(call: Call<List<Property>>, t: Throwable) {
                println(t.message)
                Toast(this@MyAdsActivity).showCustomToast("Erro ao carregar as informações!", this@MyAdsActivity)
            }

        })
    }

}