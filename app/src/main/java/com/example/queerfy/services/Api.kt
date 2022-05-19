package com.example.queerfy.services

import com.example.queerfy.model.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {
    @POST("users")
    fun registerUrgency(@Body urgency: UrgencyRegisterModel): Call<Void>

    @POST("users")
    fun registerToPlan(@Body toPlan: ToPlanRegisterModel): Call<Void>

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<User>

    @GET("properties")
    fun getProperties(): Call<List<Property>>

    @GET("properties/{id}")
    fun  getProperty(@Path("id") id: Int): Call<Property>

    @POST("users/autenticate")
    fun loginUser(@Body userLogin: LoginUserModel): Call<User>

    companion object {
        // Trocar para o ip local da maquina
        var url = "http://192.168.0.51:8080/"

        fun create(): Api {
            var retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()

            return retrofit.create(Api::class.java)
        }
    }
}