package com.example.queerfy.viewModel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.queerfy.model.NewLeaseModel
import com.example.queerfy.services.Api
import com.example.queerfy.view.NavigationDrawerActivity
import com.example.queerfy.view.showCustomToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConfirmReservartionViewModel {
    var newLeaseModel: NewLeaseModel? = null

    fun putIntoBd(modelNewLeaseModel: NewLeaseModel, context: Context) {
        this.newLeaseModel?.let {
            val postLease = Api.create().creteLease(modelNewLeaseModel)

            val homePage = Intent(context, NavigationDrawerActivity::class.java)

            postLease.enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if(response.isSuccessful) {
                        Toast(context).showCustomToast("Reserva completada com sucesso!", context as Activity)

                        context.startActivity(homePage)
                    }

                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    println(t.message)

                    Toast(context).showCustomToast("Erro ao fazer a reserva!", context as Activity)
                }

            })
        }
    }
}