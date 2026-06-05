package com.example.vaniga.domain.usecase

import androidx.paging.PagingData
import com.example.vaniga.core.common.Resource
import com.example.vaniga.domain.model.Product
import com.example.vaniga.domain.repository.ProductRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<PagingData<Product>> {
        return repository.getProductsPaging()
    }
}