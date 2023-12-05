package com.example.lokalassignment.api

import com.example.lokalassignment.model.ProductAPIResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("/products")
    fun getProducts() : Call<ProductAPIResponse>
}