package com.github.coutinhonobre.model

import com.google.gson.annotations.SerializedName

data class Cash (

    @SerializedName("title") val title : String,
    @SerializedName("bannerURL") val bannerURL : String,
    @SerializedName("description") val description : String
)