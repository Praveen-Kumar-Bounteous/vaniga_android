package com.example.vaniga.presentation.product_list

import com.example.vaniga.domain.model.Product

data class ProductListState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String = ""
)