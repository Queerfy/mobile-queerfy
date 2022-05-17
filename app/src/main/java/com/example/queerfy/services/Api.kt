package com.example.queerfy.services

import com.example.queerfy.model.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Api {
    @POST("users")
    fun registerUrgency(@Body urgency: UrgencyRegisterModel): Call<Void>

    @POST("users")
    fun registerToPlan(@Body toPlan: ToPlanRegisterModel): Call<Void>

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<User>

    @POST("users/autenticate")
    fun loginUser(@Body userLogin: LoginUserModel): Call<User>

    @GET("properties/{id}")
    fun  getProperty(@Path("id") id: Int): Call<Property>

    @POST("favorites")
    fun createFavorite(@Body newFavorite: NewFavorite): Call<Void>

    @DELETE("favorites/{id}")
    fun deleteFavorite(@Path("id") id: Int): Call<Void>


    companion object {
        // Trocar para o ip local da maquina
        var url = "http://10.18.7.9:8080/"

        fun create(): Api{
            var retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()

            return retrofit.create(Api::class.java)
        }
    }
}