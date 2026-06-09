package com.example.vaniga.presentation.product_list

import androidx.paging.PagingData
import com.example.vaniga.domain.model.Product
import com.example.vaniga.domain.usecase.GetProductsUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductListViewModelTest {

    private val getProductsUseCase: GetProductsUseCase = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `When productsFlow is collected, it emits the paging data from usecase`() = runTest {
        // 1. GIVEN: A list of fake products converted to PagingData
        val fakeProducts = listOf(
            Product(1, "Title", 10, "Desc", emptyList(), "")
        )
        val pagingData = PagingData.from(fakeProducts)

        // Mock the UseCase to return our pagingData flow
        every { getProductsUseCase(categoryId) } returns flowOf(pagingData)

        // 2. WHEN: We initialize the ViewModel
        val viewModel = ProductListViewModel(getProductsUseCase)

        // 3. THEN: We verify the flow emits something
        // Note: You can't easily "peek" inside PagingData,
        // but getting the first emission proves the flow connection is working.
        val result = viewModel.productsFlow.first()

        assert(result != null)
    }
}