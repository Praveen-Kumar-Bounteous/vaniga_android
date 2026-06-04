package com.example.vaniga.data.mapper

import com.example.vaniga.data.remote.dto.ProductDto
import com.example.vaniga.domain.model.Product

fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        images = images.map { cleanImageUrl(it) },
        thumbnail = cleanImageUrl(images.firstOrNull() ?: "")
    )
}

fun cleanImageUrl(url: String): String {
    return url.replace("[", "").replace("]", "").replace("\"", "")
}