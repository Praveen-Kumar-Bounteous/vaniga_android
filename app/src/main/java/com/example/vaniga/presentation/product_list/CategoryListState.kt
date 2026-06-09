package com.example.vaniga.presentation.product_list

import com.example.vaniga.domain.model.Category

data class CategoryListState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val error: String = ""
)