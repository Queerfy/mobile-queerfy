package com.example.queerfy.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.queerfy.R
import com.example.queerfy.databinding.ActivityMyReservationsBinding
import com.example.queerfy.model.Lease
import com.example.queerfy.model.Property
import com.example.queerfy.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyReservationsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyReservationsBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMyReservationsBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        recyclerView = findViewById(R.id.reservationsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)

        listMyReservations()

    }

    fun listMyReservations() {
        val preferences = getSharedPreferences("userPreferences", MODE_PRIVATE)

        val idUser = preferences.getInt("idUser", 0)

        val leasesGet = Api.create().getMyreservations(idUser)

        leasesGet.enqueue(object: Callback<List<Lease>> {
            override fun onResponse(
                call: Call<List<Lease>>,
                response: Response<List<Lease>>
            ) {
                if (response.isSuccessful) {
                    var myReservationsList = mutableListOf<Property>()

                    var reservations = response.body()

                    if (reservations?.isEmpty() == false) {
                        reservations.forEach { reservation ->
                            myReservationsList.add(reservation.property!!)
                        }

                        recyclerView.adapter = MyReservationsFragment(myReservationsList)

                    }else {
                        Toast(this@MyReservationsActivity).showCustomToast("Nenhuma reserva feita!", this@MyReservationsActivity)
                    }
                }
            }

            override fun onFailure(call: Call<List<Lease>>, t: Throwable) {
                Toast(this@MyReservationsActivity).showCustomToast("Ao carregar informaçãoe do usuario!", this@MyReservationsActivity)
            }

        })

    }

}