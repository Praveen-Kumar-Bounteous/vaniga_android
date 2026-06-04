package com.example.vaniga.domain.repository


import com.example.vaniga.core.common.Resource
import com.example.vaniga.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<Resource<List<Product>>>
    fun getProductById(id: Int): Flow<Resource<Product>>
}