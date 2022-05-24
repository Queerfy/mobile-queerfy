package com.example.queerfy.viewModel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.net.toFile
import com.example.queerfy.model.NewPropertyModel
import com.example.queerfy.model.Property
import com.example.queerfy.services.Api
import com.example.queerfy.view.ResidenceListActivity
import com.example.queerfy.view.showCustomToast
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class ResidenceRegisterViewModel {
    var newPropertyModel: NewPropertyModel? = null

    fun putIntoBd(newProperty: NewPropertyModel, listImages: ArrayList<Uri>, context: Context) {
        this.newPropertyModel?.let{
            val postProperty = Api.create().createProperty(newProperty)

            val homeActivity = Intent(context, ResidenceListActivity::class.java)

            postProperty.enqueue(object: Callback<Property> {
                override fun onResponse(call: Call<Property>, response: Response<Property>) {


                    val idProperty = response.body()?.id as Int

                    Toast(context).showCustomToast("Cadastrado com sucesso!", context as Activity)

                    context.startActivity(homeActivity)

//                    listImages.forEachIndexed{index, image ->
//
//                        val file = File(image.toString())
//
//                        val filePart = MultipartBody.Part.createFormData(
//                        "file",
//                            file.name,
//                            RequestBody.create(MediaType.parse("image/*"), file)
//                        )
//
//                        val postImages = Api.create().uploadImages("/properties/image${index + 1}", filePart, idProperty)
//
//                        postImages.enqueue(object: Callback<Void> {
//                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                                Toast(context).showCustomToast("Cadastrado com sucesso!", context as Activity)
//
//                                context.startActivity(homeActivity)
//                            }
//
//                            override fun onFailure(call: Call<Void>, t: Throwable) {
//                                println(t.message)
//
//                                Toast(context).showCustomToast("Erro ao cadastrar!", context as Activity)
//                            }
//
//                        })
//
//                    }

                }

                override fun onFailure(call: Call<Property>, t: Throwable) {
                    println(t.message)

                    Toast(context).showCustomToast("Erro cadastrar Residencia!", context as Activity)
                }

            })
        }
    }
}