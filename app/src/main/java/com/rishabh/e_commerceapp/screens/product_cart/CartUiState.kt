package com.rishabh.e_commerceapp.screens.product_cart

import com.rishabh.e_commerceapp.data.local.entity.CartEntity

data class CartUiState(
    val items: List<CartEntity> = emptyList(),
    val totalPrice: Double = 0.0
)
