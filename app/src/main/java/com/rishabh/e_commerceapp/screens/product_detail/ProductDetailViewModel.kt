package com.rishabh.e_commerceapp.screens.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishabh.e_commerceapp.common.Resource
import com.rishabh.e_commerceapp.domain.model.Product
import com.rishabh.e_commerceapp.domain.usecase.AddToCartUseCase
import com.rishabh.e_commerceapp.domain.usecase.GetProductDetailUseCase
import com.rishabh.e_commerceapp.domain.usecase.GetProductsUseCase
import com.rishabh.e_commerceapp.domain.usecase.RemoveFromCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProducts: GetProductsUseCase,
    private val getProductDetail: GetProductDetailUseCase,
    private val addToCart: AddToCartUseCase,
    private val removeFromCart: RemoveFromCartUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val productId: Int =
        savedStateHandle["id"] ?: error("Product ID missing")

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product.asStateFlow()

    var state = getProducts().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        Resource.Loading()
    )

    init {
        loadProduct()
    }

    private fun loadProduct() {
        viewModelScope.launch {
            _product.value = getProductDetail(productId)
        }
    }

    fun toggleCart(item: Product) {
        viewModelScope.launch {
            val current = _product.value ?: return@launch
            if (current.isInCart) {
                removeFromCart(current.id)
            } else {
                addToCart(current)
            }
            _product.value = current.copy(isInCart = !current.isInCart)
        }
    }
}
