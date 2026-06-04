package com.example.vaniga.presentation.product_list

import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import com.example.vaniga.core.common.Resource
import com.example.vaniga.domain.model.Product
import com.example.vaniga.domain.usecase.GetProductsUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class ProductListViewModelTest {

    private val getProductsUseCase: GetProductsUseCase = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When getProducts returns Success, state is updated with products`() {
        // Given
        val products = listOf(Product(1, "Title", 10, "Desc", emptyList(), ""))
        every { getProductsUseCase() } returns flowOf(Resource.Success(products))

        // When
        val viewModel = ProductListViewModel(getProductsUseCase)

        // Then
        assertEquals(products, viewModel.state.value.products)
        assertEquals(false, viewModel.state.value.isLoading)
    }

    @Test
    fun `When getProducts returns Error, state error message is updated`() {
        // Given
        val errorMsg = "Network Error"
        every { getProductsUseCase() } returns flowOf(Resource.Error(errorMsg))

        // When
        val viewModel = ProductListViewModel(getProductsUseCase)

        // Then
        assertEquals(errorMsg, viewModel.state.value.error)
        assertEquals(false, viewModel.state.value.isLoading)
    }
}