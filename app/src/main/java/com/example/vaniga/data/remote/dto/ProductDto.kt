package com.example.vaniga.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto (
    val id: Int,
    val title: String,
    val price: Int,
    val description: String,
    val images: List<String>,
)
// API Returns more fields other than above defined fields,
// Kotlinx Serializable will ignore the rest