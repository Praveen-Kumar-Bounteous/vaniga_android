package com.example.vaniga.domain.usecase

import com.example.vaniga.core.common.Resource
import com.example.vaniga.domain.model.Category
import com.example.vaniga.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<Resource<List<Category>>> {
        return repository.getCategories()
    }
}