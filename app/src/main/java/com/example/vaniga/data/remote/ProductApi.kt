package com.example.vaniga.data.remote

import com.example.vaniga.data.remote.dto.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): List<ProductDto>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto

    companion object {
        const val BASE_URL = "https://api.escuelajs.co/api/v1/"
    }
}