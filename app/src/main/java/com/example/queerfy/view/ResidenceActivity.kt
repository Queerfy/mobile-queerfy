package com.example.queerfy.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.queerfy.R
import com.example.queerfy.model.NewFavorite
import com.example.queerfy.model.Property
import com.example.queerfy.model.User
import com.example.queerfy.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.function.Predicate
import kotlin.streams.toList

class ResidenceActivity : AppCompatActivity()  {

    val houseId = 40
    var likedResidence : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_residence)

        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.fragment_residence_footer, FragmentFooter::class.java, null)
        transaction.commit()

        getHouse()
        verifyLikedHouse()
    }

    fun getHouse()  {
        // Aqui vai o id da casa dinamicamente
        val getProperty = Api.create().getProperty(houseId)

        getProperty.enqueue(object: Callback<Property> {
            override fun onResponse(call: Call<Property>, response: Response<Property>)  {
                if  (response.isSuccessful)  {
                    val propertyType = response.body()?.propertyType
                    val roomQuantity = response.body()?.roomQuantity
                    val description = response.body()?.description
                    val guestsQuantity = response.body()?.guestsQuantity
                    val bedsQuantity = response.body()?.bedsQuantity
                    val bathroomQuantity = response.body()?.bathroomQuantity
                    val dailyPrice = response.body()?.dailyPrice
                    val uf = response.body()?.uf?.uppercase()
                    val total = dailyPrice

                    val idOwner = response.body()?.idUser

                    // Caracteristicas
                    val haveWifi = response.body()?.haveWifi
                    val haveKitchen = response.body()?.haveKitchen
                    val haveSuite = response.body()?.haveSuite
                    val haveGarage = response.body()?.haveGarage
                    val haveAnimals = response.body()?.haveAnimals

                    val residenceTitle = "${propertyType} - ${roomQuantity} quarto(s) disponiveis"

                    findViewById<TextView>(R.id.residence_title).text = residenceTitle
                    findViewById<TextView>(R.id.description_text).text = description
                    findViewById<TextView>(R.id.description_details).text = "${guestsQuantity} hóspedes - ${roomQuantity} quarto - ${bedsQuantity} camas - ${bathroomQuantity} banheiro"
                    findViewById<TextView>(R.id.booking_daily_price).text = "R$ ${dailyPrice}"
                    findViewById<TextView>(R.id.booking_total_daily_price).text = "R$ ${total}"
                    findViewById<TextView>(R.id.residence_location).text = uf

                    if  (haveWifi == true)  {
                        findViewById<TextView>(R.id.wifi_characteristic).visibility = VISIBLE
                    }

                    if  (haveKitchen == true)  {
                        findViewById<TextView>(R.id.kitchen_characteristic).visibility = VISIBLE
                    }

                    if  (haveAnimals == true)  {
                        findViewById<TextView>(R.id.pets_characteristic).visibility = VISIBLE
                    }

                    if  (haveSuite == true)  {
                        findViewById<TextView>(R.id.suite_characteristic).visibility = VISIBLE
                    }

                    if  (haveGarage == true)  {
                        findViewById<TextView>(R.id.garage_characteristic).visibility = VISIBLE
                    }

                    val getOwner = Api.create().getUser(idOwner as Int)

                    getOwner.enqueue(object: Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>)  {
                            if  (response.isSuccessful)  {
                                val nameOwner = response.body()?.name

                                findViewById<TextView>(R.id.owner_residence).text = nameOwner
                            }
                        }

                        override fun onFailure(call: Call<User>, t: Throwable)  {
                            println(t.message)

                            Toast.makeText(this@ResidenceActivity, "Erro ao carregar informações do dono!", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }

            override fun onFailure(call: Call<Property>, t: Throwable)  {
                println(t.message)

                Toast.makeText(this@ResidenceActivity, "Erro na Api", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun verifyLikedHouse()  {
        val preferences = getSharedPreferences("userPreferences", MODE_PRIVATE)

        val idUser = preferences.getInt("idUser", 0)

        val getUser = Api.create().getUser(idUser)

        getUser.enqueue(object: Callback<User>{
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onResponse(call: Call<User>, response: Response<User>)  {
                if  (response.isSuccessful)  {
                    val favoritesUser = response.body()?.favorite

                    val housedLiked = favoritesUser?.stream()?.filter(Predicate { favorite -> favorite.propertyId == houseId})?.toList()

                    if  (housedLiked!!.isNotEmpty())  {
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

            override fun onFailure(call: Call<User>, t: Throwable)  {
                println(t.message)

                Toast.makeText(this@ResidenceActivity, "Erro ao carregar as informações", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun handleLikeHouse(v: View)  {
        val preferences = getSharedPreferences("userPreferences", MODE_PRIVATE)

        val idUser = preferences.getInt("idUser", 0)

        val getFavoriteUser = Api.create().getUser(idUser)

        getFavoriteUser.enqueue(object: Callback<User> {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onResponse(call: Call<User>, response: Response<User>)  {
                if  (response.isSuccessful)  {
                    val favoritesUser = response.body()?.favorite

                    val housedLiked = favoritesUser?.stream()?.filter(Predicate { favorite -> favorite.propertyId == houseId})?.toList()

                    if  (!housedLiked!!.isEmpty())  {
                        val deleteFavorite = Api.create().deleteFavorite(housedLiked[0].id as Int)

                        deleteFavorite.enqueue(object: Callback<Void>  {
                            override fun onResponse(call: Call<Void>, response: Response<Void>)  {
                                likedResidence = false

                                findViewById<ImageView>(R.id.heart_like).setImageResource(R.drawable.ic_empty_heart)

                                // Toast.makeText(this@ResidenceActivity, "Favorito deletado com sucesso!", Toast.LENGTH_SHORT).show()
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable)  {
                                println(t.message)

                                Toast.makeText(this@ResidenceActivity, "Erro ao deletar o favorito!", Toast.LENGTH_SHORT).show()
                            }
                        })
                    } else {

                        val newFavorite = NewFavorite(houseId, idUser)

                        val handleFavoriteHouse = Api.create().createFavorite(newFavorite)

                        handleFavoriteHouse.enqueue(object: Callback<Void>{
                            override fun onResponse(call: Call<Void>, response: Response<Void>)  {
                                likedResidence = true

                                findViewById<ImageView>(R.id.heart_like).setImageResource(R.drawable.colorful_heart)

                                // Toast.makeText(this@ResidenceActivity, "Favorito adicionado com sucesso!", Toast.LENGTH_SHORT).show()
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable)  {
                                println(t.message)

                                Toast.makeText(this@ResidenceActivity, "Erro ao adicionar ao favoritos", Toast.LENGTH_SHORT).show()
                            }
                        })
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable)  {
                println(t.message)

                Toast.makeText(this@ResidenceActivity, "Erro ao carregar informações do usuario", Toast.LENGTH_SHORT).show()
            }
        })
    }
}