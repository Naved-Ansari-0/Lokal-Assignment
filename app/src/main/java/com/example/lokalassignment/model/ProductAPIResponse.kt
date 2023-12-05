package com.example.lokalassignment.model

data class ProductAPIResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)