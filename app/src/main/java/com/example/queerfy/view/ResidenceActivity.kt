package com.example.queerfy.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.queerfy.R
import com.example.queerfy.model.NewFavorite
import com.example.queerfy.model.Property
import com.example.queerfy.model.User
import com.example.queerfy.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.function.Predicate
import kotlin.streams.toList
import kotlin.time.Duration

class ResidenceActivity : AppCompatActivity() {

    // val houseId = 38
    var likedResidence: Boolean = false
    var dailyPrice = 0.0
    var total = 0.0
    var diff = 0

    lateinit var datachekin: LocalDate
    lateinit var datachekout: LocalDate

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_residence)

        createFooter()
        getHouse()
        verifyLikedHouse()

        findViewById<EditText>(R.id.date_checkin).setOnTouchListener { it, motionEvent ->
            it.visibility = GONE
            findViewById<DatePicker>(R.id.dp_data_checkin).visibility = VISIBLE
            findViewById<DatePicker>(R.id.dp_data_checkin).setOnDateChangedListener { datePicker, ano, mes, dia ->

                val data = "${dia.toString().padStart(2, '0')}/${
                    (mes + 1).toString().padStart(2, '0')
                }/${ano}"
                (it as EditText).setText(data)

                datachekin = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"))

//                if (datachekin.dayOfWeek()) {
//                    Toast.makeText(this@ResidenceActivity, "Data Invalida!", Toast.LENGTH_SHORT).show()
//                }

                datePicker.visibility = GONE
                it.visibility = VISIBLE
            }
            false
        }

        findViewById<EditText>(R.id.date_checkout).setOnTouchListener { it, motionEvent ->
            it.visibility = GONE
            findViewById<DatePicker>(R.id.dp_data_checkout).visibility = VISIBLE
            findViewById<DatePicker>(R.id.dp_data_checkout).setOnDateChangedListener { datePicker, ano, mes, dia ->

                val data = "${dia.toString().padStart(2, '0')}/${
                    (mes + 1).toString().padStart(2, '0')
                }/${ano}"
                (it as EditText).setText(data)
                datachekout = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"))

                diff = datachekout.compareTo(datachekin)

                if (diff < 0) {
                    Toast.makeText(this@ResidenceActivity, "Data Invalida!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    total = dailyPrice * diff

                    findViewById<TextView>(R.id.booking_total_daily_price).text = "R$ ${"%,.2f".format(total)}"
                }

                datePicker.visibility = GONE
                it.visibility = VISIBLE
            }
            false
        }
    }

    fun createFooter() {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.fragment_residence_footer, FragmentFooter::class.java, null)
        transaction.commit()
    }

    fun getHouse() {
        val confirmationActivity = Intent(this@ResidenceActivity, ConfirmationActivity::class.java)

        // Aqui vai o id da casa dinamicamente

        val houseId = intent.getIntExtra("idHouse", 0)

        val getProperty = Api.create().getProperty(houseId)

        getProperty.enqueue(object : Callback<Property> {
            override fun onResponse(call: Call<Property>, response: Response<Property>) {
                if (response.isSuccessful) {
                    val propertyType = response.body()?.propertyType
                    val roomQuantity = response.body()?.roomQuantity
                    val description = response.body()?.description
                    val guestsQuantity = response.body()?.guestsQuantity
                    val bedsQuantity = response.body()?.bedsQuantity
                    val bathroomQuantity = response.body()?.bathroomQuantity
                    val priceDaily = response.body()?.dailyPrice
                    val uf = response.body()?.uf?.uppercase()

                    dailyPrice = priceDaily as Double

                    val idOwner = response.body()?.idUser

                    // Caracteristicas
                    val haveWifi = response.body()?.haveWifi
                    val haveKitchen = response.body()?.haveKitchen
                    val haveSuite = response.body()?.haveSuite
                    val haveGarage = response.body()?.haveGarage
                    val haveAnimals = response.body()?.haveAnimals

                    val residenceTitle = "${propertyType} - ${roomQuantity} quarto(s) disponiveis"
                    val residenteDetails = "${guestsQuantity} hóspedes - ${roomQuantity} quarto - ${bedsQuantity} camas - ${bathroomQuantity} banheiro"

                    findViewById<TextView>(R.id.residence_title).text = residenceTitle
                    findViewById<TextView>(R.id.description_text).text = description
                    findViewById<TextView>(R.id.description_details).text = residenteDetails
                    findViewById<TextView>(R.id.booking_daily_price).text = "R$ ${dailyPrice}"
                    findViewById<TextView>(R.id.booking_total_daily_price).text = "R$ ${dailyPrice}"
                    findViewById<TextView>(R.id.residence_location).text = uf

                    if (haveWifi == true) {
                        findViewById<TextView>(R.id.wifi_characteristic).visibility = VISIBLE
                    }

                    if (haveKitchen == true) {
                        findViewById<TextView>(R.id.kitchen_characteristic).visibility = VISIBLE
                    }

                    if (haveAnimals == true) {
                        findViewById<TextView>(R.id.pets_characteristic).visibility = VISIBLE
                    }

                    if (haveSuite == true) {
                        findViewById<TextView>(R.id.suite_characteristic).visibility = VISIBLE
                    }

                    if (haveGarage == true) {
                        findViewById<TextView>(R.id.garage_characteristic).visibility = VISIBLE
                    }

                    val getOwner = Api.create().getUser(idOwner as Int)

                    getOwner.enqueue(object : Callback<User> {
                        @RequiresApi(Build.VERSION_CODES.O)
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            if (response.isSuccessful) {
                                val nameOwner = response.body()?.name

                                findViewById<TextView>(R.id.owner_residence).text = nameOwner

                                findViewById<Button>(R.id.button_confirm).setOnClickListener { it ->
                                    confirmationActivity.putExtra("residenceTitle", residenceTitle)
                                    confirmationActivity.putExtra("residenteDetails", residenteDetails)
                                    confirmationActivity.putExtra("uf", uf)
                                    confirmationActivity.putExtra("datachekin", datachekin)
                                    confirmationActivity.putExtra("datachekout", datachekout)
                                    confirmationActivity.putExtra("dailyPrice", dailyPrice)
                                    confirmationActivity.putExtra("dailys", diff)
                                    confirmationActivity.putExtra("total", total)

                                    startActivity(confirmationActivity)
                                }
                            }
                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {
                            println(t.message)

                            Toast.makeText(
                                this@ResidenceActivity,
                                "Erro ao carregar informações do dono!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                }
            }

            override fun onFailure(call: Call<Property>, t: Throwable) {
                println(t.message)

                Toast.makeText(this@ResidenceActivity, "Erro na Api", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun verifyLikedHouse() {
        val preferences = getSharedPreferences("userPreferences", MODE_PRIVATE)

        val idUser = preferences.getInt("idUser", 0)

        val houseId = intent.getIntExtra("idHouse", 0)

        val getUser = Api.create().getUser(idUser)

        getUser.enqueue(object : Callback<User> {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val favoritesUser = response.body()?.favorite

                    val housedLiked = favoritesUser?.stream()
                        ?.filter(Predicate { favorite -> favorite.propertyId == houseId })?.toList()

                    if (housedLiked!!.isNotEmpty()) {
                        likedResidence = true

                        findViewById<ImageView>(R.id.heart_like).setImageResource(R.drawable.colorful_heart)

                        // Toast.makeText(this@ResidenceActivity, "Casa já favoritada!", Toast.LENGTH_SHORT).show()
                    } else {
                        likedResidence = false

                        findViewById<ImageView>(R.id.heart_like).setImageResource(R.drawable.ic_empty_heart)

                        // Toast.makeText(this@ResidenceActivity, "Casa ainda não favoritada!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                println(t.message)

                Toast.makeText(
                    this@ResidenceActivity,
                    "Erro ao carregar as informações",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    fun handleLikeHouse(v: View) {
        val preferences = getSharedPreferences("userPreferences", MODE_PRIVATE)

        val houseId = intent.getIntExtra("idHouse", 0)

        val idUser = preferences.getInt("idUser", 0)

        val getFavoriteUser = Api.create().getUser(idUser)

        getFavoriteUser.enqueue(object : Callback<User> {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val favoritesUser = response.body()?.favorite

                    val housedLiked = favoritesUser?.stream()
                        ?.filter(Predicate { favorite -> favorite.propertyId == houseId })?.toList()

                    if (!housedLiked!!.isEmpty()) {
                        val deleteFavorite = Api.create().deleteFavorite(housedLiked[0].id as Int)

                        deleteFavorite.enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                likedResidence = false

                                findViewById<ImageView>(R.id.heart_like).setImageResource(R.drawable.ic_empty_heart)

                                // Toast.makeText(this@ResidenceActivity, "Favorito deletado com sucesso!", Toast.LENGTH_SHORT).show()
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                println(t.message)

                                Toast.makeText(
                                    this@ResidenceActivity,
                                    "Erro ao deletar o favorito!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                    } else {

                        val newFavorite = NewFavorite(houseId, idUser)

                        val handleFavoriteHouse = Api.create().createFavorite(newFavorite)

                        handleFavoriteHouse.enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                likedResidence = true

                                findViewById<ImageView>(R.id.heart_like).setImageResource(R.drawable.colorful_heart)

                                // Toast.makeText(this@ResidenceActivity, "Favorito adicionado com sucesso!", Toast.LENGTH_SHORT).show()
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                println(t.message)

                                Toast.makeText(
                                    this@ResidenceActivity,
                                    "Erro ao adicionar ao favoritos",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                println(t.message)

                Toast.makeText(
                    this@ResidenceActivity,
                    "Erro ao carregar informações do usuario",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

}