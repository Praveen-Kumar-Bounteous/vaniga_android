package com.example.vaniga.presentation.product_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.vaniga.core.common.Resource
import com.example.vaniga.domain.repository.ProductRepository
import com.example.vaniga.domain.usecase.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val getCategoriesUseCase: GetCategoriesUseCase // Make sure this is injected
) : ViewModel() {

    private val _selectedCategory = MutableStateFlow<Int?>(null)
    val selectedCategory = _selectedCategory.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val productsFlow = _selectedCategory.flatMapLatest { categoryId ->
        repository.getProductsPaging(categoryId)
    }.cachedIn(viewModelScope)

    private val _categoriesState = mutableStateOf(CategoryListState())
    val categoriesState: State<CategoryListState> = _categoriesState

    init {
        fetchCategories()
    }

    fun onCategorySelected(id: Int?) {
        _selectedCategory.value = id
    }

    private fun fetchCategories() {
        getCategoriesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _categoriesState.value = CategoryListState(categories = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _categoriesState.value = CategoryListState(isLoading = true)
                }
                is Resource.Error -> {
                    _categoriesState.value = CategoryListState(error = result.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}


//    private val _state = mutableStateOf(ProductListState())
//    val state: State<ProductListState> = _state
//
//    init {
//        getProducts()
//    }

//    fun getProducts() {
//        getProductsUseCase().onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _state.value = ProductListState(products = result.data ?: emptyList())
//                }
//                is Resource.Error -> {
//                    _state.value = ProductListState(error = result.message ?: "An unexpected error occurred")
//                }
//                is Resource.Loading -> {
//                    _state.value = ProductListState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
