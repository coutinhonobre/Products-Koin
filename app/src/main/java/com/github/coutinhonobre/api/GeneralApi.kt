package com.github.coutinhonobre.api

import com.github.coutinhonobre.model.General
import retrofit2.Call
import retrofit2.http.GET

interface GeneralApi {

    @GET("products")
    fun getProducts(): Call<General>

}