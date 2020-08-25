package com.github.coutinhonobre.model

import com.google.gson.annotations.SerializedName

data class General(
    @SerializedName("spotlight") val spotlight : MutableList<Spotlight>,
    @SerializedName("products") val products : MutableList<Product>,
    @SerializedName("cash") val cash : Cash
)