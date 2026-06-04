package com.example.vaniga.domain.model

data class Product (
    val id: Int,
    val title: String,
    val price: Int,
    val description: String,
    val images: List<String>,
    val thumbnail: String
)
