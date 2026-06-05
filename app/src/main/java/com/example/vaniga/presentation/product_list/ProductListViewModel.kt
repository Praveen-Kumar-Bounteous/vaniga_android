package com.example.vaniga.presentation.product_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.vaniga.core.common.Resource
import com.example.vaniga.domain.model.Product
import com.example.vaniga.domain.repository.ProductRepository
import com.example.vaniga.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    val productsFlow: Flow<PagingData<Product>> = getProductsUseCase()
        .cachedIn(viewModelScope)

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
}