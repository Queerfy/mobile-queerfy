package com.example.queerfy.view

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.queerfy.R
import com.example.queerfy.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResidenceListActivity : AppCompatActivity() {

    private lateinit var recyclerViewTrendOne: RecyclerView
    private lateinit var recyclerViewResidence: RecyclerView

    private lateinit var wifiButton: Button
    private lateinit var kitchenButton: Button
    private lateinit var suiteButton: Button
    private lateinit var garageButton: Button
    private lateinit var animalButton: Button

    private val colorFont: String = "#1A1A1A"
    private val colorBlue: String = "#439EFA"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_residence_list)

        val city = intent.getStringExtra("city") as String
        val cityNotFormated = intent.getStringExtra("cityNotFormated")

        findViewById<TextView>(R.id.residence_list_trend_title).text =
            "Locações com as melhores avaliações em $cityNotFormated"

        recyclerViewTrendOne = findViewById(R.id.trend_residences_list)
        recyclerViewTrendOne.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewTrendOne.itemAnimator = DefaultItemAnimator()
        recyclerViewTrendOne.setHasFixedSize(true)

        recyclerViewResidence = findViewById(R.id.residences_list)
        recyclerViewResidence.layoutManager = LinearLayoutManager(baseContext)
        recyclerViewResidence.itemAnimator = DefaultItemAnimator()
        recyclerViewResidence.setHasFixedSize(true)

        wifiButton = findViewById(R.id.btn_have_wifi)
        kitchenButton = findViewById(R.id.btn_have_kitchen)
        suiteButton = findViewById(R.id.btn_have_suite)
        garageButton = findViewById(R.id.btn_have_garage)
        animalButton = findViewById(R.id.btn_have_animal)

        val getProperties = Api.create().getResidencesSearch(city)
        val residencesTrendList = mutableListOf<com.example.queerfy.model.Property>()
        val residencesListSearch = mutableListOf<com.example.queerfy.model.Property>()

        getProperties.enqueue(object : Callback<List<com.example.queerfy.model.Property>> {
            override fun onResponse(
                call: Call<List<com.example.queerfy.model.Property>>,
                response: Response<List<com.example.queerfy.model.Property>>
            ) {
                if (response.isSuccessful) {
                    val residenciesList = response.body()

                    if (residenciesList!!.isNotEmpty()) {
                        residenciesList.forEach { property ->
                            if (property.likes!! > 1000) {
                                findViewById<LinearLayout>(R.id.residence_list_trending_container).visibility =
                                    View.VISIBLE

                                findViewById<TextView>(R.id.residence_list_trend_title).visibility =
                                    View.VISIBLE

                                findViewById<TextView>(R.id.residence_list_trend_subtitle).visibility =
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

    private fun setButtonHighlight(button: Button) {
        wifiButton.setTextColor(
            Color.parseColor(colorFont)
        )
        kitchenButton.setTextColor(
            Color.parseColor(colorFont)
        )
        suiteButton.setTextColor(
            Color.parseColor(colorFont)
        )
        garageButton.setTextColor(
            Color.parseColor(colorFont)
        )
        animalButton.setTextColor(
            Color.parseColor(colorFont)
        )

        button.setTextColor(
            Color.parseColor(colorBlue)
        )
    }

    fun getHaveWifiResidences(v: View) {
        setButtonHighlight(wifiButton)

        val city = intent.getStringExtra("city") as String
        val getProperties = Api.create().getResidencesSearch(city)
        val residencesListSearch = mutableListOf<com.example.queerfy.model.Property>()

        getProperties.enqueue(object : Callback<List<com.example.queerfy.model.Property>> {
            override fun onResponse(
                call: Call<List<com.example.queerfy.model.Property>>,
                response: Response<List<com.example.queerfy.model.Property>>
            ) {
                if (response.isSuccessful) {
                    val residenciesList = response.body()

                    if (residenciesList!!.isNotEmpty()) {
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
        setButtonHighlight(kitchenButton)

        val city = intent.getStringExtra("city") as String
        val getProperties = Api.create().getResidencesSearch(city)
        val residencesListSearch = mutableListOf<com.example.queerfy.model.Property>()

        getProperties.enqueue(object : Callback<List<com.example.queerfy.model.Property>> {
            override fun onResponse(
                call: Call<List<com.example.queerfy.model.Property>>,
                response: Response<List<com.example.queerfy.model.Property>>
            ) {
                if (response.isSuccessful) {
                    val residenciesList = response.body()

                    if (residenciesList!!.isNotEmpty()) {
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
        setButtonHighlight(suiteButton)

        val city = intent.getStringExtra("city") as String
        val getProperties = Api.create().getResidencesSearch(city)
        val residencesListSearch = mutableListOf<com.example.queerfy.model.Property>()

        getProperties.enqueue(object : Callback<List<com.example.queerfy.model.Property>> {
            override fun onResponse(
                call: Call<List<com.example.queerfy.model.Property>>,
                response: Response<List<com.example.queerfy.model.Property>>
            ) {
                if (response.isSuccessful) {
                    val residenciesList = response.body()

                    if (residenciesList!!.isNotEmpty()) {
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
        setButtonHighlight(garageButton)

        val city = intent.getStringExtra("city") as String
        val getProperties = Api.create().getResidencesSearch(city)
        val residencesListSearch = mutableListOf<com.example.queerfy.model.Property>()

        getProperties.enqueue(object : Callback<List<com.example.queerfy.model.Property>> {
            override fun onResponse(
                call: Call<List<com.example.queerfy.model.Property>>,
                response: Response<List<com.example.queerfy.model.Property>>
            ) {
                if (response.isSuccessful) {
                    val residenciesList = response.body()

                    if (residenciesList!!.isNotEmpty()) {
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
        setButtonHighlight(animalButton)

        val city = intent.getStringExtra("city") as String
        val getProperties = Api.create().getResidencesSearch(city)
        val residencesListSearch = mutableListOf<com.example.queerfy.model.Property>()

        getProperties.enqueue(object : Callback<List<com.example.queerfy.model.Property>> {
            override fun onResponse(
                call: Call<List<com.example.queerfy.model.Property>>,
                response: Response<List<com.example.queerfy.model.Property>>
            ) {
                if (response.isSuccessful) {
                    val residenciesList = response.body()

                    if (residenciesList!!.isNotEmpty()) {
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