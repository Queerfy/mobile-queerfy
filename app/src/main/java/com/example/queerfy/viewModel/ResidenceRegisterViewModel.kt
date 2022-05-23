package com.example.queerfy.viewModel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.queerfy.model.NewPropertyModel
import com.example.queerfy.services.Api
import com.example.queerfy.view.ResidenceListActivity
import com.example.queerfy.view.showCustomToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResidenceRegisterViewModel {
    var newPropertyModel: NewPropertyModel? = null

    fun putIntoBd(newProperty: NewPropertyModel, context: Context) {
        this.newPropertyModel?.let{
            val postProperty = Api.create().createProperty(newProperty)

            val homeActivity = Intent(context, ResidenceListActivity::class.java)

            postProperty.enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast(context).showCustomToast("Cadastrado com sucesso!", context as Activity)

                    context.startActivity(homeActivity)
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    println(t.message)

                    Toast(context).showCustomToast("Erro cadastrar Residencia!", context as Activity)
                }

            })
        }
    }
}