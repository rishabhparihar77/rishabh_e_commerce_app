package com.rishabh.e_commerceapp.screens.product_cart


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishabh.e_commerceapp.data.local.dao.CartDao
import com.rishabh.e_commerceapp.domain.model.Product
import com.rishabh.e_commerceapp.domain.usecase.GetCartItemsUseCase
import com.rishabh.e_commerceapp.domain.usecase.RemoveFromCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    val getCartItems: GetCartItemsUseCase,
    private val removeFromCart: RemoveFromCartUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow<CartUiState?>(null)
    val uiState: StateFlow<CartUiState?> = _uiState.asStateFlow()

    init {
        loadProduct()
    }

/*
    val uiState: StateFlow<CartUiState> = getCartItems()
        .map { CartUiState(it, it.sumOf { item -> item.price }) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CartUiState())
*/



    private fun loadProduct() {
        viewModelScope.launch {
            getCartItems()
                .map { CartUiState(it, it.sumOf { item -> item.price }) }.collect { result ->
                    _uiState.value = result
                }
        }
    }


    fun remove(id: Int) = viewModelScope.launch {
        removeFromCart(id)
        loadProduct()
    }
}