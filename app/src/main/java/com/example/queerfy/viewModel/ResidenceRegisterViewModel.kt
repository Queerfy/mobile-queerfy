package com.example.queerfy.viewModel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.queerfy.model.NewPropertyModel
import com.example.queerfy.model.Property
import com.example.queerfy.services.Api
import com.example.queerfy.view.NavigationDrawerActivity
import com.example.queerfy.view.showCustomToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ResidenceRegisterViewModel {
    var newPropertyModel: NewPropertyModel? = null

    fun putIntoBd(newProperty: NewPropertyModel, listImages: ArrayList<Uri>, context: Context) {
        this.newPropertyModel?.let {
            val postProperty = Api.create().createProperty(newProperty)

            val homeActivity = Intent(context, NavigationDrawerActivity::class.java)

            postProperty.enqueue(object : Callback<Property> {
                override fun onResponse(call: Call<Property>, response: Response<Property>) {
                    Toast(context).showCustomToast("Cadastrado com sucesso!", context as Activity)

                    context.startActivity(homeActivity)
                }

                override fun onFailure(call: Call<Property>, t: Throwable) {
                    println(t.message)

                    Toast(context).showCustomToast(
                        "Erro cadastrar Residencia!",
                        context as Activity
                    )
                }

            })
        }
    }
}