package com.github.coutinhonobre.model

import com.google.gson.annotations.SerializedName

data class Product (

    @SerializedName("name") val name : String,
    @SerializedName("imageURL") val imageURL : String,
    @SerializedName("description") val description : String
)
