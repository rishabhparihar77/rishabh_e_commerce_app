package com.rishabh.e_commerceapp.screens.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishabh.e_commerceapp.common.Resource
import com.rishabh.e_commerceapp.domain.model.Product
import com.rishabh.e_commerceapp.domain.usecase.AddToCartUseCase
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
class ProductListViewModel @Inject constructor(
    private val getProducts: GetProductsUseCase,
    private val addToCart: AddToCartUseCase,
    private val removeFromCart: RemoveFromCartUseCase
) : ViewModel() {

    private val _product = MutableStateFlow<Resource<List<Product>>>(Resource.Loading())
    val state: StateFlow<Resource<List<Product>>> = _product.asStateFlow()

    init {
        loadProduct()
    }

    private fun loadProduct() {
        viewModelScope.launch {
            getProducts().collect { result ->
                _product.value = result
            }
        }
    }
/*
    var state = getProducts().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        Resource.Loading()
    )
*/

    fun toggleCart(product: Product) {
        viewModelScope.launch {
            if (product.isInCart)
                removeFromCart(product.id)
            else
                addToCart(product)
        }
        loadProduct()
    }
}
