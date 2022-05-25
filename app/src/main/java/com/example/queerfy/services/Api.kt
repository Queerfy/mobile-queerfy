package com.example.queerfy.services

import com.example.queerfy.model.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Api {
    @POST("users")
    fun registerUrgency(@Body urgency: UrgencyRegisterModel): Call<Void>

    @POST("users")
    fun registerToPlan(@Body toPlan: ToPlanRegisterModel): Call<Void>

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<User>

    @POST("users/autenticate")
    fun loginUser(@Body userLogin: LoginUserModel): Call<User>

    @PUT("users/update/{id}")
    fun updateUser(@Path("id") id: Int, @Body updateUser: UpdateUserModel): Call<User>

    @POST("properties")
    fun createProperty(@Body propertyModel: NewPropertyModel): Call<Property>

    @GET("properties")
    fun getProperties(): Call<List<Property>>

    @GET("properties/{id}")
    fun  getProperty(@Path("id") id: Int): Call<Property>

    @DELETE("properties/{id}")
    fun deleteProperty(@Path("id") id: Int): Call<Void>

    @Multipart
    @PATCH("properties/image{index}/{id}")
    fun uploadImages(@Path("index") index: Int, @Part filePart: MultipartBody.Part, @Path("id") id: Int): Call<Void>

    @POST("favorites")
    fun createFavorite(@Body newFavorite: NewFavorite): Call<Void>

    @DELETE("favorites/{id}")
    fun deleteFavorite(@Path("id") id: Int): Call<Void>

    @GET("leases/{id}")
    fun getMyreservations(@Path("id") id: Int): Call<List<Property>>

    companion object {
        // Trocar para o ip local da maquina
        //var url = "http://192.168.0.51:8080/"
        var url = "http://10.18.7.9:8080/"

        fun create(): Api {
            var retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()

            return retrofit.create(Api::class.java)
        }
    }
}