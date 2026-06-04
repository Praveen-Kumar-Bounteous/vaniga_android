package com.example.vaniga.presentation.product_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.example.vaniga.core.common.Resource
import com.example.vaniga.domain.repository.ProductRepository
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(ProductDetailState())
    val state: State<ProductDetailState> = _state

    init {
        fetchProduct()
    }

    fun fetchProduct() {
        val productId = savedStateHandle.get<Int>("productId")
        if (productId != null) {
            getProduct(productId)
        } else {
            _state.value = ProductDetailState(error = "Invalid Product ID")
        }
    }

    private fun getProduct(id: Int) {
        repository.getProductById(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ProductDetailState(product = result.data)
                }
                is Resource.Error -> {
                    _state.value = ProductDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ProductDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}