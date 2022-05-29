package com.example.queerfy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Property
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.queerfy.R
import com.example.queerfy.model.Favorite
import com.example.queerfy.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResidenceListActivity : AppCompatActivity() {

    private lateinit var recyclerViewTrendOne: RecyclerView
    private lateinit var recyclerViewResidence: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_residence_list)

        val city = intent.getStringExtra("city") as String
        val cityNotFormated = intent.getStringExtra("cityNotFormated")

        findViewById<TextView>(R.id.residence_list_trend_title).text =
            "Locações com as melhores avaliações em ${cityNotFormated}"

        recyclerViewTrendOne = findViewById(R.id.trend_residences_list)
        recyclerViewTrendOne.layoutManager = LinearLayoutManager(baseContext)
        recyclerViewTrendOne.itemAnimator = DefaultItemAnimator()
        recyclerViewTrendOne.setHasFixedSize(true)

        recyclerViewResidence = findViewById(R.id.residences_list)
        recyclerViewResidence.layoutManager = LinearLayoutManager(baseContext)
        recyclerViewResidence.itemAnimator = DefaultItemAnimator()
        recyclerViewResidence.setHasFixedSize(true)

        val getProperties = Api.create().getResidencesSearch(city)

        var residencesTrendList = mutableListOf<com.example.queerfy.model.Property>()

        var residencesListSearch = mutableListOf<com.example.queerfy.model.Property>()

        getProperties.enqueue(object : Callback<List<com.example.queerfy.model.Property>> {
            override fun onResponse(
                call: Call<List<com.example.queerfy.model.Property>>,
                response: Response<List<com.example.queerfy.model.Property>>
            ) {
                if (response.isSuccessful) {

                    val residenciesList = response.body()

                    if (!residenciesList!!.isEmpty()) {

                        residenciesList.forEach { property ->

                            if (property.likes!! > 1000) {
                                findViewById<TextView>(R.id.residence_list_trend_title).visibility =
                                    View.VISIBLE
                                findViewById<TextView>(R.id.residence_list_trend_subtitle).visibility =
                                    View.VISIBLE
                                findViewById<TextView>(R.id.residence_list_trending_container).visibility =
                                    View.VISIBLE
                                residencesTrendList.add(property)
                            }

                            residencesListSearch.add(property)


                        }

                        recyclerViewTrendOne.adapter = TrendResidenceFragment(residencesTrendList)

                        recyclerViewResidence.adapter = ResidenceFragment(residencesListSearch)

                    }
                }
            }

            override fun onFailure(
                call: Call<List<com.example.queerfy.model.Property>>,
                t: Throwable
            ) {
                Toast.makeText(
                    this@ResidenceListActivity,
                    "Erro ao carregar as residencias!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })

    }

    fun getHaveWifiResidences(v: View) {

        val city = intent.getStringExtra("city") as String

        val getProperties = Api.create().getResidencesSearch(city)

        var residencesListSearch = mutableListOf<com.example.queerfy.model.Property>()

        getProperties.enqueue(object : Callback<List<com.example.queerfy.model.Property>> {
            override fun onResponse(
                call: Call<List<com.example.queerfy.model.Property>>,
                response: Response<List<com.example.queerfy.model.Property>>
            ) {
                if (response.isSuccessful) {

                    val residenciesList = response.body()

                    if (!residenciesList!!.isEmpty()) {

                        residenciesList.forEach { property ->

                            if (property.haveWifi == true) {
                                residencesListSearch.add(property)
                            }

                        }

                        recyclerViewResidence.adapter = ResidenceFragment(residencesListSearch)

                    }
                }
            }

            override fun onFailure(
                call: Call<List<com.example.queerfy.model.Property>>,
                t: Throwable
            ) {
                Toast.makeText(
                    this@ResidenceListActivity,
                    "Erro ao carregar as residencias!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    fun getHaveKitchenResidences(v: View) {

        val city = intent.getStringExtra("city") as String

        val getProperties = Api.create().getResidencesSearch(city)

        var residencesListSearch = mutableListOf<com.example.queerfy.model.Property>()

        getProperties.enqueue(object : Callback<List<com.example.queerfy.model.Property>> {
            override fun onResponse(
                call: Call<List<com.example.queerfy.model.Property>>,
                response: Response<List<com.example.queerfy.model.Property>>
            ) {
                if (response.isSuccessful) {

                    val residenciesList = response.body()

                    if (!residenciesList!!.isEmpty()) {

                        residenciesList.forEach { property ->

                            if (property.haveKitchen == true) {
                                residencesListSearch.add(property)
                            }

                        }

                        recyclerViewResidence.adapter = ResidenceFragment(residencesListSearch)

                    }
                }
            }

            override fun onFailure(
                call: Call<List<com.example.queerfy.model.Property>>,
                t: Throwable
            ) {
                Toast.makeText(
                    this@ResidenceListActivity,
                    "Erro ao carregar as residencias!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    fun getHaveSuiteResidences(v: View) {

        val city = intent.getStringExtra("city") as String

        val getProperties = Api.create().getResidencesSearch(city)

        var residencesListSearch = mutableListOf<com.example.queerfy.model.Property>()

        getProperties.enqueue(object : Callback<List<com.example.queerfy.model.Property>> {
            override fun onResponse(
                call: Call<List<com.example.queerfy.model.Property>>,
                response: Response<List<com.example.queerfy.model.Property>>
            ) {
                if (response.isSuccessful) {

                    val residenciesList = response.body()

                    if (!residenciesList!!.isEmpty()) {

                        residenciesList.forEach { property ->

                            if (property.haveSuite == true) {
                                residencesListSearch.add(property)
                            }

                        }

                        recyclerViewResidence.adapter = ResidenceFragment(residencesListSearch)

                    }
                }
            }

            override fun onFailure(
                call: Call<List<com.example.queerfy.model.Property>>,
                t: Throwable
            ) {
                Toast.makeText(
                    this@ResidenceListActivity,
                    "Erro ao carregar as residencias!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    fun getHaveGarageResidences(v: View) {

        val city = intent.getStringExtra("city") as String

        val getProperties = Api.create().getResidencesSearch(city)

        var residencesListSearch = mutableListOf<com.example.queerfy.model.Property>()

        getProperties.enqueue(object : Callback<List<com.example.queerfy.model.Property>> {
            override fun onResponse(
                call: Call<List<com.example.queerfy.model.Property>>,
                response: Response<List<com.example.queerfy.model.Property>>
            ) {
                if (response.isSuccessful) {

                    val residenciesList = response.body()

                    if (!residenciesList!!.isEmpty()) {

                        residenciesList.forEach { property ->

                            if (property.haveGarage == true) {
                                residencesListSearch.add(property)
                            }

                        }

                        recyclerViewResidence.adapter = ResidenceFragment(residencesListSearch)

                    }
                }
            }

            override fun onFailure(
                call: Call<List<com.example.queerfy.model.Property>>,
                t: Throwable
            ) {
                Toast.makeText(
                    this@ResidenceListActivity,
                    "Erro ao carregar as residencias!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    fun getHaveAnimalsResidences(v: View) {

        val city = intent.getStringExtra("city") as String

        val getProperties = Api.create().getResidencesSearch(city)

        var residencesListSearch = mutableListOf<com.example.queerfy.model.Property>()

        getProperties.enqueue(object : Callback<List<com.example.queerfy.model.Property>> {
            override fun onResponse(
                call: Call<List<com.example.queerfy.model.Property>>,
                response: Response<List<com.example.queerfy.model.Property>>
            ) {
                if (response.isSuccessful) {

                    val residenciesList = response.body()

                    if (!residenciesList!!.isEmpty()) {

                        residenciesList.forEach { property ->

                            if (property.haveAnimals == true) {
                                residencesListSearch.add(property)
                            }

                        }

                        recyclerViewResidence.adapter = ResidenceFragment(residencesListSearch)

                    }
                }
            }

            override fun onFailure(
                call: Call<List<com.example.queerfy.model.Property>>,
                t: Throwable
            ) {
                Toast.makeText(
                    this@ResidenceListActivity,
                    "Erro ao carregar as residencias!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

}