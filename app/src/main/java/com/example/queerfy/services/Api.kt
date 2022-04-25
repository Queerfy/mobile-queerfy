package com.example.queerfy.services

import com.example.queerfy.model.LoginUserModel
import com.example.queerfy.model.ToPlanRegisterModel
import com.example.queerfy.model.UrgencyRegisterModel
import com.example.queerfy.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    @POST("users")
    fun registerUrgency(@Body urgency: UrgencyRegisterModel): Call<Void>

    @POST("users")
    fun registerToPlan(@Body toPlan: ToPlanRegisterModel): Call<Void>

    @POST("autenticate")
    fun loginUser(@Body userLogin: LoginUserModel): Call<User>

    companion object {
        // Trocar para o ip local da maquina
        var url = "http:/192.168.0.51:8080/"

        fun create(): Api {
            var retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()

            return retrofit.create(Api::class.java)
        }
    }
}