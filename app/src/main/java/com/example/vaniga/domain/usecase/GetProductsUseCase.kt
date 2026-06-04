package com.example.vaniga.domain.usecase

import com.example.vaniga.core.common.Resource
import com.example.vaniga.domain.model.Product
import com.example.vaniga.domain.repository.ProductRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<Resource<List<Product>>> {
        return repository.getProducts()
    }
}