package com.example.vaniga.domain.repository


import androidx.paging.PagingData
import com.example.vaniga.core.common.Resource
import com.example.vaniga.domain.model.Category
import com.example.vaniga.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProductsPaging(categoryId: Int?): Flow<PagingData<Product>>
    fun getProductById(id: Int): Flow<Resource<Product>>
    fun getCategories(): Flow<Resource<List<Category>>>
}