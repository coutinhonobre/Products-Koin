package com.github.coutinhonobre.repository

import com.github.coutinhonobre.api.GeneralApi


class ProductRepository(private val api: GeneralApi) {
    fun getAllProducts() = api.getProducts()
}