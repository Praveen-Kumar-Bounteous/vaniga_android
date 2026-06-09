package com.example.vaniga.data.remote

import com.example.vaniga.data.remote.dto.CategoryDto
import com.example.vaniga.data.remote.dto.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("products")
    suspend fun getProducts(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("categoryId") categoryId: Int? = null
    ): List<ProductDto>

    @GET("categories")
    suspend fun getCategories(): List<CategoryDto>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto

    companion object {
        const val BASE_URL = "https://api.escuelajs.co/api/v1/"
    }
}