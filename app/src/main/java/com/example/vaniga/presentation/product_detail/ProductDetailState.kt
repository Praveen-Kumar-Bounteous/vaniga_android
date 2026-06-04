package com.example.vaniga.presentation.product_detail

import com.example.vaniga.domain.model.Product

data class ProductDetailState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String = ""
)