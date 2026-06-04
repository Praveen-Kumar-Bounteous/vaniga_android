package com.example.vaniga.data.mapper

import com.example.vaniga.data.remote.dto.ProductDto
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductMapperTest {

    @Test
    fun `ProductDto toDomain correctly cleans image URLs`() {
        // Given a DTO with messy URL strings from the API
        val dto = ProductDto(
            id = 1,
            title = "Test",
            price = 100,
            description = "Desc",
            images = listOf("[\"https://test.com/img.jpg\"]")
        )

        // When
        val domain = dto.toDomain()

        // Then
        assertEquals("https://test.com/img.jpg", domain.thumbnail)
        assertEquals("https://test.com/img.jpg", domain.images[0])
    }
}