package com.rishabh.e_commerceapp.domain.repository

import com.rishabh.e_commerceapp.common.Resource
import com.rishabh.e_commerceapp.data.local.entity.CartEntity
import com.rishabh.e_commerceapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<Resource<List<Product>>>
    fun getCartItems(): Flow<List<CartEntity>>
    suspend fun addToCart(product: Product)
    suspend fun removeFromCart(productId: Int)
    suspend fun getProductById(id: Int): Product

}